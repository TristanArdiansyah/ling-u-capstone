package com.bangkit.capstone.lingu.view.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.lifecycleScope
import com.bangkit.capstone.lingu.data.RetrofitClient
import com.bangkit.capstone.lingu.data.progress.ProgressResponse
import com.bangkit.capstone.lingu.view.course.AllCourseActivity
import com.bangkit.capstone.lingu.databinding.ActivityMainBinding
import com.bangkit.capstone.lingu.view.profile.ProfileActivity
import com.bangkit.capstone.lingu.view.ViewModelFactory
import com.bangkit.capstone.lingu.view.canvas.CanvasActivity
import com.bangkit.capstone.lingu.view.course.DetailCourseActivity
import com.bangkit.capstone.lingu.view.login.LoginActivity
import com.bangkit.capstone.lingu.view.welcome.WelcomeActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }

            auth = Firebase.auth
            val firebaseUser = auth.currentUser
            if (firebaseUser == null && !user.isLogin) {
                // Not signed in, launch the Login activity
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }

            setupView(user.displayName, user.lastCourse)
        }
        setupAction()
        getSyncData()

    }

    private fun setupView(displayName: String, lastCourse: String) {
        binding.greetingTextView.text = "Halo, $displayName"
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        if (lastCourse.isNotEmpty()) {
            try {
                val lastCourseId = lastCourse.toInt()
                viewModel.getCourseAndCharactersById(lastCourseId).observe(this) { course ->
                    binding.courseNameTextView.text = course.course.name
                    binding.courseImageView.setImageResource(course.course.imageResId)
                    binding.courseCharacters.text = "${course.characters.size} characters"
                    binding.determinateBar.progress = 25
                    binding.continueButton.setOnClickListener {
                        val detailIntent = Intent(this@MainActivity, DetailCourseActivity::class.java)
                        detailIntent.putExtra(DetailCourseActivity.EXTRA_COURSE_ID, course.course.courseId)
                        startActivity(detailIntent)
                    }
                }
            } catch (e: NumberFormatException) {
                Log.e("MainActivity", "NumberFormatException: Invalid input for integer conversion", e)
                // Handle the exception, maybe show an error message or use a default value
            }
        } else {
            Log.e("MainActivity", "Empty string for lastCourse, cannot convert to integer")
            // Handle the empty string case, maybe show an error message or use a default value
        }

        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.searchButtonNonFill.setOnClickListener {
            val intent = Intent(this, AllCourseActivity::class.java)
            startActivity(intent)
        }

        binding.profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionId, event ->
                searchBar.setText(searchView.text)
                searchView.hide()
                val query = textView.text.toString()
                searchModule(query)
                true
            }
        }
    }

    private fun searchModule(query: String) {
        if (query.isBlank()) {
            // If the query is blank, do nothing
            return
        }
        // Implement search logic here
    }

    private fun getSyncData() {
        viewModel.getSession().observe(this@MainActivity){user->
            val token = user.token
            lifecycleScope.launch {
                try {
                    val response = RetrofitClient.instance.progress(token)
                    applySyncData(response)
                } catch (e: HttpException) {
                    signOut()
                    finish()
                }

            }
        }

    }
    private fun applySyncData(progressResponse: ProgressResponse){
        for (category in  progressResponse.data) {
            if (category.characters != null){
                for (progressCharacter in category.characters) {
                    viewModel.getCharactersDetailByHanzi(progressCharacter!!.character).observe(this){character->
                        lifecycleScope.launch {
                            character.bestScore = progressCharacter.confidenceScore.toFloat()
                            if (character.bestScore>=0.9f) {
                                character.isDone = true
                            }
                            viewModel.update(character)
                        }
                    }
                }
            }
        }
    }

    private fun signOut() {
        lifecycleScope.launch {
            val credentialManager = CredentialManager.create(this@MainActivity)
            auth.signOut()
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
            viewModel.resetData()
        }
        viewModel.logout()
    }
}
