package com.example.habittracker.view

import android.view.View
import com.example.habittracker.model.Habit

interface HabitCardListener {
    fun onIncreaseClick(habit: Habit)
    fun onDecreaseClick(habit: Habit)
    fun onTitleClick(habit: Habit)
}

interface EditHabitListener {
    fun onSaveClick(view: View)
}