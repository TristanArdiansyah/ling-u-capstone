package com.bangkit.capstone.lingu.view.profile

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.lifecycleScope
import com.bangkit.capstone.lingu.R
import com.bangkit.capstone.lingu.view.course.AllCourseActivity
import com.bangkit.capstone.lingu.databinding.ActivityProfileBinding
import com.bangkit.capstone.lingu.view.ViewModelFactory
import com.bangkit.capstone.lingu.view.login.LoginActivity
import com.bangkit.capstone.lingu.view.main.MainActivity
import com.bangkit.capstone.lingu.view.main.MainViewModel
import com.bangkit.capstone.lingu.view.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }

            auth = FirebaseAuth.getInstance()
            val firebaseUser = auth.currentUser
            if (firebaseUser == null && !user.isLogin) {
                // Not signed in, launch the Login activity
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }

        setupAction()
        playAnimation()
    }

    private fun setupAction() {
        binding.logoutButton.setOnClickListener {
            lifecycleScope.launch {
                val credentialManager = CredentialManager.create(this@ProfileActivity)
                auth.signOut()
                credentialManager.clearCredentialState(ClearCredentialStateRequest())
                startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
                finish()
                viewModel.resetData()
            }
            viewModel.logout()
        }

        binding.homeButtonNonFilled.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.searchButtonNonFill.setOnClickListener {
            val intent = Intent(this, AllCourseActivity::class.java)
            startActivity(intent)
        }

    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val logout = ObjectAnimator.ofFloat(binding.logoutButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(logout)
            startDelay = 100
        }.start()
    }
}

