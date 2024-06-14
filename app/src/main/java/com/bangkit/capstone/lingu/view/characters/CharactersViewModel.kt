package com.bangkit.capstone.lingu.view.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.capstone.lingu.data.CharactersRepository
import com.bangkit.capstone.lingu.data.database.Characters

class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel()  {
    fun getCharacterById(charId: Int): LiveData<Characters> = charactersRepository.getCharactersById(charId)
}