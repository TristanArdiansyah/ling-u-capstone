package com.bangkit.capstone.lingu.view.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bangkit.capstone.lingu.R
import com.bangkit.capstone.lingu.data.RetrofitClient
import com.bangkit.capstone.lingu.data.register.RegisterRequest
import com.bangkit.capstone.lingu.databinding.ActivitySignupBinding
import com.bangkit.capstone.lingu.view.login.LoginActivity
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        playAnimation()
        setupLoginLink()
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
        binding.signupButton.setOnClickListener {
            val fullName = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

            if (password != confirmPassword) {
                AlertDialog.Builder(this@SignupActivity).apply {
                    setTitle("Error")
                    setMessage("Passwords does not match.")
                    setPositiveButton("OK", null)
                    create()
                    show()
                }
            } else {
                binding.progressBarSignUp.visibility = View.VISIBLE
                register(fullName, email, password, confirmPassword)
            }
        }
    }

    private fun register(fullName: String, email: String, password: String, confirmPassword: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.register(fullName, email, password, confirmPassword)
                binding.progressBarSignUp.visibility = View.GONE
                if (response.status == "success") {
                    // Handle success
                    AlertDialog.Builder(this@SignupActivity).apply {
                        setTitle("Success!")
                        setMessage("Registration successful.")
                        setPositiveButton("OK") { _, _ ->
                            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        create()
                        show()
                    }
                } else {
                    // Handle error
                    AlertDialog.Builder(this@SignupActivity).apply {
                        setTitle("Error")
                        setMessage(response.message ?: "Registration failed.")
                        setPositiveButton("OK", null)
                        create()
                        show()
                    }
                }
            } catch (e: Exception) {
                binding.progressBarSignUp.visibility = View.GONE
                // Handle exception
                AlertDialog.Builder(this@SignupActivity).apply {
                    setTitle("Error")
                    setMessage(e.message ?: "An error occurred.")
                    setPositiveButton("OK", null)
                    create()
                    show()
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
        val nameTextView =
            ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(100)
        val nameEditTextLayout =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val confirmPasswordTextView =
            ObjectAnimator.ofFloat(binding.confirmPasswordTextView, View.ALPHA, 1f).setDuration(100)
        val confirmPasswordEditTextLayout =
            ObjectAnimator.ofFloat(binding.confirmPasswordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(100)
        val message =
            ObjectAnimator.ofFloat(binding.toLoginPage, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                nameTextView,
                nameEditTextLayout,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                confirmPasswordTextView,
                confirmPasswordEditTextLayout,
                signup,
                message
            )
            startDelay = 100
        }.start()
    }

    private fun setupLoginLink() {
        val textView = findViewById<TextView>(R.id.toLoginPage)
        val alreadyHaveAccountText = getString(R.string.already_have_account)

        val spannableString = SpannableString(alreadyHaveAccountText)
        val start = alreadyHaveAccountText.indexOf("Masuk")
        val end = start + "Masuk".length

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        spannableString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance() // This makes the link clickable
    }
}
