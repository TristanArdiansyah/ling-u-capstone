package com.bangkit.capstone.lingu.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.lingu.data.CharactersRepository
import com.bangkit.capstone.lingu.data.UserRepository
import com.bangkit.capstone.lingu.data.database.Characters
import com.bangkit.capstone.lingu.data.database.CharactersAndCourse
import com.bangkit.capstone.lingu.data.database.Course
import com.bangkit.capstone.lingu.data.database.CourseAndCharacters
import com.bangkit.capstone.lingu.data.pref.UserModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository, private val charactersRepository: CharactersRepository) : ViewModel() {
    init {
        insertAllData()
    }
    private fun insertAllData() = viewModelScope.launch {
        charactersRepository.insertAllData()
    }


    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun getCourseBySlug(slug: String): LiveData<Course> = charactersRepository.getCourseDetailBySlug(slug)

    fun getCourseAndCharactersById(courseId: Int): LiveData<CourseAndCharacters> = charactersRepository.getCourseAndCharactersById(courseId)

    suspend fun update(characters: Characters) {
        charactersRepository.update(characters)
    }

    suspend fun update(course: Course) {
        charactersRepository.updateCourse(course)
    }
    fun getCharactersDetailByHanzi(hanzi: String): LiveData<Characters> = charactersRepository.getCharactersByHanzi(hanzi)

    suspend fun resetData() = charactersRepository.resetAllData()

}