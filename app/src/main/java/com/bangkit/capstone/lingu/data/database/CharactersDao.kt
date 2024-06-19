package com.bangkit.capstone.lingu.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characters: List<Characters>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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
    fun getCharacterDetailByid(query: Int): LiveData<Characters>

    @Update
    suspend fun updateCharacter(character: Characters)
}
