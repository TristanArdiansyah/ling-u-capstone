package com.bangkit.capstone.lingu.view.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.capstone.lingu.data.CharactersRepository
import com.bangkit.capstone.lingu.data.UserRepository
import com.bangkit.capstone.lingu.data.database.Characters
import com.bangkit.capstone.lingu.data.database.CharactersAndCourse
import com.bangkit.capstone.lingu.data.pref.UserModel

class CharactersViewModel(private val userRepository: UserRepository, private val charactersRepository: CharactersRepository) : ViewModel()  {
    fun getCharacterById(charId: Int): LiveData<Characters> = charactersRepository.getCharactersById(charId)

    fun getCharactersAndCourseById(charactersId: Int) :LiveData<CharactersAndCourse> = charactersRepository.getCharactersAndCourseById(charactersId)

    suspend fun update(characters: Characters) {
        charactersRepository.update(characters)
    }
    fun getSession(): LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }
}