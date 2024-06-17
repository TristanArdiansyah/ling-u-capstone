package com.bangkit.capstone.lingu.view.course

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstone.lingu.R
import com.bangkit.capstone.lingu.databinding.ActivityDetailCourseBinding
import com.bangkit.capstone.lingu.view.ViewModelFactory
import com.bangkit.capstone.lingu.view.characters.DetailCharactersActivity
import com.bangkit.capstone.lingu.view.main.MainActivity
import com.bangkit.capstone.lingu.view.profile.ProfileActivity

class DetailCourseActivity : AppCompatActivity() {
    private val viewModel by viewModels<CourseViewModel> {
        ViewModelFactory.getInstance(this)
    }
    companion object {
        const val EXTRA_COURSE_ID = "EXTRA_COURSE_ID"
    }
    private lateinit var binding: ActivityDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCourseBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
        setupAction()
    }
    private fun setupAction(){
        binding.homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
        binding.searchButtonFilled.setOnClickListener {
            val intent = Intent(this, AllCourseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
        binding.profileButton.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.backButton.setOnClickListener{
            finish()
        }
    }
    private fun setupView(){
        val courseId = intent.getIntExtra(EXTRA_COURSE_ID, 0)
        viewModel.getCourseAndCharactersById(courseId).observe(this) { course ->
            binding.tvCourseName.text = course.course.name
            binding.imageView.setImageResource(course.course.imageResId)
            binding.tvCharactersCount.text = course.characters.size.toString() + " characters"
            val charactersAdapter = CharactersAdapter(course.characters){ characters ->
//                 Handle class click
                val detailCharIntent = Intent(this@DetailCourseActivity, DetailCharactersActivity::class.java)
                detailCharIntent.putExtra(DetailCharactersActivity.EXTRA_CHARACTERS_ID, characters.characterId)
                detailCharIntent.putExtra(DetailCharactersActivity.EXTRA_COURSE_ID,  courseId)
                startActivity(detailCharIntent)
            }
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@DetailCourseActivity)
                adapter = charactersAdapter
            }
        }
    }
}