package com.example.habittracker.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habittracker.model.Habit
import com.example.habittracker.model.User

@Database(
    entities = [
        Habit::class,
        User::class
    ],
    version = 2,
    exportSchema = false
)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: HabitDatabase? = null

        private val LOCK = Any()

        fun getInstance(context: Context): HabitDatabase {
            return INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    "habit_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}