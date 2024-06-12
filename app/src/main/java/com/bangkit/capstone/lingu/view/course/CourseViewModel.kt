package com.bangkit.capstone.lingu.view.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.capstone.lingu.data.CharactersRepository
import com.bangkit.capstone.lingu.data.UserRepository
import com.bangkit.capstone.lingu.data.database.Characters
import com.bangkit.capstone.lingu.data.database.CharactersAndCourse
import com.bangkit.capstone.lingu.data.database.Course
import com.bangkit.capstone.lingu.data.database.CourseAndCharacters
import com.bangkit.capstone.lingu.data.pref.UserModel

class CourseViewModel(private val repository: UserRepository, private val charactersRepository: CharactersRepository) : ViewModel()  {
    fun getAllCharacters(): LiveData<List<Characters>> = charactersRepository.getAllCharacters()

    fun getAllCourse(): LiveData<List<Course>> = charactersRepository.getAllCourse()

    fun getAllCharactersAndCourse(): LiveData<List<CharactersAndCourse>> = charactersRepository.getAllCharactersAndCourse()

    fun getAllCourseAndCharacters(): LiveData<List<CourseAndCharacters>> = charactersRepository.getAllCourseAndCharacters()

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}