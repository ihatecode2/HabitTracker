package com.example.habittracker.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.habittracker.model.Habit

@Dao
interface HabitDao {

    @Query("SELECT * FROM habits")
    fun getAll(): List<Habit>

    @Query("SELECT * FROM habits WHERE id = :id")
    fun getById(id: Int): Habit?

    @Insert
    fun insert(habit: Habit)

    @Update
    fun update(habit: Habit)

    @Query(" UPDATE habits SET progress = :progress WHERE id = :id")
    fun updateProgress(id: Int, progress: Int)
}