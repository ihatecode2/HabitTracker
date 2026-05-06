package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habittracker.model.Habit

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    val habitList = MutableLiveData<ArrayList<Habit>>(arrayListOf())

    fun addHabit(habit: Habit) {
        val list = habitList.value ?: arrayListOf()
        list.add(habit)
        habitList.value = list
    }

    fun refresh() {
        habitList.value = habitList.value
    }
}
