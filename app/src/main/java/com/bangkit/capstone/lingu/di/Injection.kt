package com.bangkit.capstone.lingu.di

import android.content.Context
import com.bangkit.capstone.lingu.data.UserRepository
import com.bangkit.capstone.lingu.data.pref.UserPreference
import com.bangkit.capstone.lingu.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}