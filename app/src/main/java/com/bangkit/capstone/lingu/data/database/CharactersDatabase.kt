package com.bangkit.capstone.lingu.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Characters::class, Course::class], version = 7, exportSchema = false)
abstract class CharactersDatabase: RoomDatabase() {
    abstract fun CharactersDao(): CharactersDao;

    companion object {
        @Volatile
        private var INSTANCE: CharactersDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): CharactersDatabase {
            if (INSTANCE == null) {
                synchronized(CharactersDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CharactersDatabase::class.java, "characters_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as CharactersDatabase
        }

    }
}