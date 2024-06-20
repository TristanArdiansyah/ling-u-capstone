package com.bangkit.capstone.lingu.view.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bangkit.capstone.lingu.R
import com.bangkit.capstone.lingu.data.RetrofitClient
import com.bangkit.capstone.lingu.data.login.LoginResponse
import com.bangkit.capstone.lingu.data.pref.UserModel
import com.bangkit.capstone.lingu.databinding.ActivityLoginBinding
import com.bangkit.capstone.lingu.view.ViewModelFactory
import com.bangkit.capstone.lingu.view.main.MainActivity
import com.bangkit.capstone.lingu.view.signup.SignupActivity
import com.bangkit.capstone.lingu.view.welcome.WelcomeActivity
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModelProvider
import com.bangkit.capstone.lingu.view.main.MainViewModel
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.signInButton.visibility = View.GONE
        binding.frameLayout.visibility = View.GONE

        setupView()
        setupAction()
        playAnimation()
        setupSignupLink()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            loginWithEmailPassword(email, password)
        }

        binding.signInButton.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun loginWithEmailPassword(email: String, password: String) {
        binding.progressBarLogin.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.login(email, password)
                binding.progressBarLogin.visibility = View.GONE
                if (response.status == "success") {
                    // Handle success
                    Log.d(TAG, "loginWithEmail:success")
                    val userModel = UserModel(uid = response.data.uid, displayName = response.data.fullName, email = response.data.email, token = response.data.token, isLogin = true, lastCourse = "1")
                    viewModel.saveSession(userModel)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // Handle error
                    Log.w(TAG, "loginWithEmail:failure")
                    Toast.makeText(this@LoginActivity, "Authentication failed: ${response.message}", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            } catch (e: Exception) {
                binding.progressBarLogin.visibility = View.GONE
                // Handle exception
                Log.w(TAG, "loginWithEmail:failure", e)
                Toast.makeText(this@LoginActivity, "Authentication failed: ${e.message}", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    private fun signInWithGoogle() {
        val credentialManager = CredentialManager.create(this)
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(getString(R.string.web_client_id))
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                val result: GetCredentialResponse = credentialManager.getCredential(
                    request = request,
                    context = this@LoginActivity,
                )
                handleGoogleSignIn(result)
            } catch (e: GetCredentialException) {
                Log.d(TAG, e.message.toString())
                Toast.makeText(this@LoginActivity, "Failed to get Google credential: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleGoogleSignIn(result: GetCredentialResponse) {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val idToken = (credential as GoogleIdTokenCredential).idToken
                    firebaseAuthWithGoogle(idToken)
                } else {
                    Log.e(TAG, "Unexpected type of credential")
                    Toast.makeText(this, "Unexpected type of credential", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                Log.e(TAG, "Unexpected type of credential")
                Toast.makeText(this, "Unexpected type of credential", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user: FirebaseUser? = auth.currentUser
                    if (user != null) {
                        val displayName = user.displayName ?: user.email?.substringBefore('@') ?: "User"
                        val userModel = UserModel(user.uid, displayName, user.email ?: "", idToken, true, lastCourse = "1")
                        viewModel.saveSession(userModel)
                        updateUI(user)
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        Toast.makeText(this, "Authentication failed: ${task.exception?.message}" , Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
            }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val message =
            ObjectAnimator.ofFloat(binding.toLoginPage, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)
        val loginGoogle = ObjectAnimator.ofFloat(binding.signInButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login,
                loginGoogle,
                message
            )
            startDelay = 100
        }.start()

        Handler().postDelayed({
            binding.signInButton.visibility = View.VISIBLE
            binding.frameLayout.visibility = View.VISIBLE
        }, 700) // Delay in milliseconds
    }

    private fun setupSignupLink() {
        val textView = findViewById<TextView>(R.id.toLoginPage)
        val didNotHaveAccountText = getString(R.string.did_not_have_account)

        val spannableString = SpannableString(didNotHaveAccountText)
        val start = didNotHaveAccountText.indexOf("Daftar")
        val end = start + "Daftar".length

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, SignupActivity::class.java)
                startActivity(intent)
            }
        }

        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun updateUI(currentUser: FirebaseUser? = null) {
        if (currentUser != null) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()

        } else {
            startActivity(Intent(this@LoginActivity, WelcomeActivity::class.java))
            finish()
        }
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}
