package com.bangkit.capstone.lingu.di

import android.content.Context
import com.bangkit.capstone.lingu.data.UserRepository
import com.bangkit.capstone.lingu.data.pref.UserPreference

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context)
        return UserRepository.getInstance(pref)
    }
}
