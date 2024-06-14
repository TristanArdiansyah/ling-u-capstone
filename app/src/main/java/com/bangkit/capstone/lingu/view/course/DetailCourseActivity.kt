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
                startActivity(detailCharIntent)
            }
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@DetailCourseActivity)
                adapter = charactersAdapter
            }
        }
    }
}