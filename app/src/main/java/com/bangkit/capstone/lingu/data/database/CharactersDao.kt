package com.bangkit.capstone.lingu.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<Characters>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: List<Course>)

    @Transaction
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): LiveData<List<Characters>>

    @Transaction
    @Query("SELECT * from course")
    fun getAllCourse(): LiveData<List<Course>>

    @Transaction
    @Query("SELECT * from characters")
    fun getAllCharactersAndCourse(): LiveData<List<CharactersAndCourse>>

    @Transaction
    @Query("SELECT * from course")
    fun getAllCourseAndCharacters(): LiveData<List<CourseAndCharacters>>

    @Transaction
    @Query("SELECT * from course WHERE courseId = :query")
    fun getCourseDetailByid(query: Int): LiveData<CourseAndCharacters>

    @Transaction
    @Query("SELECT * from characters WHERE characterId = :query")
    fun getCharacterAndCourseDetailById(query: Int): LiveData<CharactersAndCourse>

    @Transaction
    @Query("SELECT * from characters WHERE characterId = :query")
    fun getCharacterDetailByid(query: Int): LiveData<Characters>

    @Transaction
    @Query("SELECT * from characters WHERE hanzi = :query")
    fun getCharacterDetailByHanzi(query: String): LiveData<Characters>

    @Transaction
    @Query("SELECT * from course WHERE slug = :query")
    fun getCourseDetailBySlug(query: String): LiveData<Course>


    @Update
    suspend fun updateCharacter(character: Characters)
    @Update
    suspend fun updateCourse(course: Course)
    @Query("UPDATE characters SET bestScore = 0.0")
    suspend fun resetBestScoreOfCharacters()
    @Query("UPDATE characters SET isDone = 0")
    suspend fun resetIsDoneOfCharactersForAll()
}
