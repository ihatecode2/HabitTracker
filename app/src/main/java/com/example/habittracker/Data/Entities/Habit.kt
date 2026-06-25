package com.example.habittracker.Data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "progress")
    val progress:Int,
    @ColumnInfo(name = "goal")
    val goal: Int,
    @ColumnInfo(name = "unit")
    val unit: String,
    @ColumnInfo(name = "icon")
    val icon: String,
    @ColumnInfo(name = "created_at")
    val createdAt: LocalDate = LocalDate.now(),
    @ColumnInfo(name = "updated_at")
    val updatedAt: LocalDate = LocalDate.now(),
    @ColumnInfo(name = "deleted_at")
    val deletedAt: LocalDate
)
