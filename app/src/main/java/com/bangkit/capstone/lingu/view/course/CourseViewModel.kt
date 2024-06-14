package com.bangkit.capstone.lingu.view.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Query
import com.bangkit.capstone.lingu.data.CharactersRepository
import com.bangkit.capstone.lingu.data.database.Characters
import com.bangkit.capstone.lingu.data.database.CharactersAndCourse
import com.bangkit.capstone.lingu.data.database.Course
import com.bangkit.capstone.lingu.data.database.CourseAndCharacters

class CourseViewModel(private val charactersRepository: CharactersRepository) : ViewModel()  {

    fun getAllCourse(): LiveData<List<Course>> = charactersRepository.getAllCourse()
    fun getCourseAndCharactersById(courseId: Int): LiveData<CourseAndCharacters> = charactersRepository.getCourseAndCharactersById(courseId)
}