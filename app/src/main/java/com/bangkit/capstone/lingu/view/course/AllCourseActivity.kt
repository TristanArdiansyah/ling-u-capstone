package com.bangkit.capstone.lingu.view.course

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstone.lingu.R
import com.bangkit.capstone.lingu.data.database.Course
import com.bangkit.capstone.lingu.databinding.ActivityAllCourseBinding
import com.bangkit.capstone.lingu.view.ViewModelFactory
import com.bangkit.capstone.lingu.view.profile.ProfileActivity
import com.bangkit.capstone.lingu.view.main.MainActivity
import com.bangkit.capstone.lingu.view.main.MainViewModel

class AllCourseActivity : AppCompatActivity() {
    private val viewModel by viewModels<CourseViewModel> {
        ViewModelFactory.getInstance(this)
    }
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
        viewModel.getSession().observe(this){user ->
            binding.greetingTextView.text = "Halo, " + user.displayName
        }
        viewModel.getAllCourse().observe(this) {listCourses ->
            val courseAdapter = CourseAdapter(listCourses){ course ->
                // Handle enroll button click
                // Example: Toast.makeText(this, "Enrolled in ${course.name}", Toast.LENGTH_SHORT).show()
            }
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@AllCourseActivity)
                adapter = courseAdapter
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
    }

    private fun searchModule(query: String) {
        if (query.isBlank()) {
            //klo kosong ngapain
            return
        }
    }

}
