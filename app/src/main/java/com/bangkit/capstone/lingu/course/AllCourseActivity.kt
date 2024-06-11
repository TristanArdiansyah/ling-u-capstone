package com.bangkit.capstone.lingu.course

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstone.lingu.R
import com.bangkit.capstone.lingu.data.pref.CourseModel
import com.bangkit.capstone.lingu.databinding.ActivityAllCourseBinding
import com.bangkit.capstone.lingu.databinding.ActivityMainBinding
import com.bangkit.capstone.lingu.profile.ProfileActivity
import com.bangkit.capstone.lingu.view.main.MainActivity

class AllCourseActivity : AppCompatActivity() {

    private lateinit var courseAdapter: CourseAdapter
    private lateinit var binding: ActivityAllCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupAction()
        setupView()
    }

    private fun setupAction(){
        binding.homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.profileButton.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupView(){
        val courses = listOf(
            CourseModel(1, "Location", R.drawable.location_image),
            CourseModel(2, "Body Parts", R.drawable.body_image),
            CourseModel(3, "Arithmetic", R.drawable.arithmetic_image),
            CourseModel(4, "Nature", R.drawable.nature_image),
            CourseModel(5, "Conversational", R.drawable.conversation_image),
        )

        courseAdapter = CourseAdapter(courses) { course ->
            // Handle enroll button click
            // Example: Toast.makeText(this, "Enrolled in ${course.name}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AllCourseActivity)
            adapter = courseAdapter
        }
    }

}
