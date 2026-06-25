package com.example.habittracker.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.habittracker.model.Habit

/*
*/
@Dao
interface HabitDao {

    @Query("SELECT * FROM habits")
    suspend fun getAll(): List<Habit>

    @Query("SELECT * FROM habits WHERE id = :id")
    suspend fun getById(id: Long): Habit?

    @Insert
    suspend fun insert(habit: Habit): Long

    @Update
    suspend fun update(habit: Habit): Int

    @Delete
    suspend fun delete(habit: Habit): Int
}