package com.bangkit.capstone.lingu.view.course

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstone.lingu.data.database.Course
import com.bangkit.capstone.lingu.databinding.ItemAllCourseBinding

class CourseAdapter(private val courses: List<Course>, private val onEnrollClick: (Course) -> Unit) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemAllCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int = courses.size

    inner class CourseViewHolder(private val binding: ItemAllCourseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(courseModel: Course) {
            binding.courseNameTextView.text = courseModel.name
            binding.courseImageView.setImageResource(courseModel.imageResId)
            Log.d("CourseAdapter", "Binding course: ${courseModel.name}, ImageResId: ${courseModel.imageResId}")
            binding.enrollButton.setOnClickListener { onEnrollClick(courseModel) }
        }
    }
}
