package com.example.habittracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "goal")
    var goal: Int,

    @ColumnInfo(name = "progress")
    var progress: Int = 0,

    @ColumnInfo(name = "unit")
    var unit: String,

    @ColumnInfo(name = "icon")
    var icon: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @get:Ignore
    val isCompleted: Boolean
        get() = progress >= goal

    @get:Ignore
    val progressPercentage: Int
        get() {
            return if (goal > 0) {
                (progress * 100) / goal
            } else {
                0
            }
        }

    @get:Ignore
    val progressText: String
        get() = "$progress / $goal $unit"

    fun isValid(): Boolean {
        return name.isNotBlank() &&
                description.isNotBlank() &&
                unit.isNotBlank() &&
                goal > 0
    }
}