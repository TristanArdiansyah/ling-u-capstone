package com.bangkit.capstone.lingu.data.database

import androidx.room.*

@Entity
data class Characters(
    @PrimaryKey
    val characterId: Int,
    val hanzi: String,
    val pinyin: String,
    val english: String,
    val example_hanzi: String,
    val example_pinyin: String,
    val example_english: String,
    val part_of_speech: String,
    val coId: Int,
    val idOnCourse: Int
)

@Entity
data class Course(
    @PrimaryKey
    val courseId: Int,
    val name: String,
    val imageResId: Int
)

data class CharactersAndCourse(
    @Embedded
    val characters: Characters,

    @Relation(
        parentColumn = "coId",
        entityColumn = "courseId"
    )
    val course: Course? = null
)

data class CourseAndCharacters(
    @Embedded
    val course: Course,

    @Relation(
        parentColumn = "courseId",
        entityColumn = "coId"
    )
    val characters: List<Characters>
)

