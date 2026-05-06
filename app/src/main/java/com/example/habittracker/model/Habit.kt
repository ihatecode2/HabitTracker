package com.example.habittracker.model

data class Habit(
    var name: String,
    var description: String,
    var goal: Int,
    var unit: String,
    var icon: Int,
    var progress: Int = 0
) {

    val isCompleted: Boolean
        get() = progress >= goal

    val progressPercentage: Int
        get() = if (goal > 0) (progress * 100) / goal else 0

    val progressText: String
        get() = "$progress / $goal $unit"

    fun isValid(): Boolean {
        return name.isNotBlank()
                && description.isNotBlank()
                && unit.isNotBlank()
                && goal > 0
    }

    fun increment() {
        if (progress < goal) {
            progress++
        }
    }

    fun decrement() {
        if (progress > 0) {
            progress--
        }
    }
}