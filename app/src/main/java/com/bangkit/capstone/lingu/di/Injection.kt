package com.bangkit.capstone.lingu.di

import android.content.Context
import com.bangkit.capstone.lingu.data.CharactersRepository
import com.bangkit.capstone.lingu.data.UserRepository
import com.bangkit.capstone.lingu.data.database.CharactersDao
import com.bangkit.capstone.lingu.data.database.CharactersDatabase
import com.bangkit.capstone.lingu.data.pref.UserPreference

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context)
        return UserRepository.getInstance(pref)
    }
    fun provideCharactersRepository(context: Context): CharactersRepository {
        val database = CharactersDatabase.getDatabase(context)
        val dao = database.CharactersDao()
        return CharactersRepository.getInstance(dao)
    }
}
