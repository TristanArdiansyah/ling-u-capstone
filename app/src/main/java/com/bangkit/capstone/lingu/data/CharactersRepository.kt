package com.bangkit.capstone.lingu.data

import androidx.lifecycle.LiveData
import com.bangkit.capstone.lingu.data.database.Course
import com.bangkit.capstone.lingu.data.database.CourseAndCharacters
import com.bangkit.capstone.lingu.data.database.Characters
import com.bangkit.capstone.lingu.data.database.CharactersAndCourse
import com.bangkit.capstone.lingu.data.database.CharactersDao
import com.bangkit.capstone.lingu.data.database.helper.InitialDataSource
import com.bangkit.capstone.lingu.data.pref.UserPreference

class CharactersRepository(private val charactersDao: CharactersDao) {
    fun getAllCharacters(): LiveData<List<Characters>> = charactersDao.getAllCharacters()

    fun getAllCourse(): LiveData<List<Course>> = charactersDao.getAllCourse()

    fun getAllCharactersAndCourse(): LiveData<List<CharactersAndCourse>> = charactersDao.getAllCharactersAndCourse()

    fun getAllCourseAndCharacters(): LiveData<List<CourseAndCharacters>> = charactersDao.getAllCourseAndCharacters()
    fun getCourseAndCharactersById(courseId: Int): LiveData<CourseAndCharacters> = charactersDao.getCourseDetailByid(courseId)

    fun getCharactersAndCourseById(charactersId: Int): LiveData<CharactersAndCourse> = charactersDao.getCharacterAndCourseDetailById(charactersId)
    fun getCharactersById(charId: Int): LiveData<Characters> = charactersDao.getCharacterDetailByid(charId)

    fun getCharactersByHanzi(hanzi: String): LiveData<Characters> = charactersDao.getCharacterDetailByHanzi(hanzi)

    fun getCourseDetailBySlug(slug: String): LiveData<Course> = charactersDao.getCourseDetailBySlug(slug)

    suspend fun updateCourse(course: Course) {
        charactersDao.updateCourse(course)
    }
    suspend fun update(character: Characters) {
        charactersDao.updateCharacter(character)
    }

    suspend fun insertAllData() {
        charactersDao.insertCourse(InitialDataSource.getCourse())
        charactersDao.insertCharacters(InitialDataSource.getCharacters())
    }

    suspend fun resetAllData() {
        charactersDao.resetIsDoneOfCharactersForAll()
        charactersDao.resetBestScoreOfCharacters()
    }
    companion object {
        @Volatile
        private var instance: CharactersRepository? = null
        fun getInstance(
            charactersDao: CharactersDao
        ): CharactersRepository =
            instance ?: synchronized(this) {
                instance ?: CharactersRepository(charactersDao)
            }.also { instance = it }
    }
}