package com.bangkit.capstone.lingu.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.capstone.lingu.data.CharactersRepository
import com.bangkit.capstone.lingu.data.UserRepository
import com.bangkit.capstone.lingu.data.database.CharactersDao
import com.bangkit.capstone.lingu.di.Injection
import com.bangkit.capstone.lingu.view.characters.CharactersViewModel
import com.bangkit.capstone.lingu.view.course.CourseViewModel
import com.bangkit.capstone.lingu.view.login.LoginViewModel
import com.bangkit.capstone.lingu.view.main.MainViewModel

class ViewModelFactory(private val repository: UserRepository, private val charactersRepository: CharactersRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository, charactersRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CourseViewModel::class.java) -> {
                CourseViewModel(repository, charactersRepository) as T
            }
            modelClass.isAssignableFrom(CharactersViewModel::class.java) -> {
                CharactersViewModel(repository, charactersRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context), Injection.provideCharactersRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}