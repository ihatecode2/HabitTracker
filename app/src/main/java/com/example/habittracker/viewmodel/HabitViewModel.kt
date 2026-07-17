package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habittracker.Data.HabitDatabase
import com.example.habittracker.model.Habit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HabitViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val habitList = MutableLiveData<ArrayList<Habit>>(arrayListOf())

    val selectedHabit = MutableLiveData<Habit>()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        launch {
            val database = HabitDatabase.getInstance(getApplication())

            val habits = database.habitDao().getAll()

            habitList.postValue(ArrayList(habits))
        }
    }

    fun addHabit(habit: Habit) {
        launch {
            val database = HabitDatabase.getInstance(getApplication())

            database.habitDao().insert(habit)

            val habits = database.habitDao().getAll()

            habitList.postValue(ArrayList(habits))
        }
    }

    fun fetchHabit(id: Int) {
        launch {
            val database = HabitDatabase.getInstance(getApplication())

            val habit = database.habitDao().getById(id)

            if (habit != null) {
                selectedHabit.postValue(habit)
            }
        }
    }

    fun updateHabit(habit: Habit) {
        launch {
            val database = HabitDatabase.getInstance(getApplication())

            database.habitDao().update(habit)

            val habits = database.habitDao().getAll()

            habitList.postValue(ArrayList(habits))
        }
    }

    fun increaseProgress(habit: Habit) {
        if (habit.progress < habit.goal) {
            updateProgress(habit, habit.progress + 1
            )
        }
    }

    fun decreaseProgress(habit: Habit) {
        if (habit.progress > 0) {
            updateProgress(habit, habit.progress - 1
            )
        }
    }

    private fun updateProgress(
        habit: Habit,
        newProgress: Int
    ) {
        launch {
            val database = HabitDatabase.getInstance(getApplication())

            database.habitDao().updateProgress(habit.id, newProgress
            )

            val habits = database.habitDao().getAll()

            habitList.postValue(ArrayList(habits))
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
