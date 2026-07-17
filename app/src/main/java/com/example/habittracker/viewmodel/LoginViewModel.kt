package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habittracker.Data.HabitDatabase
import com.example.habittracker.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val loginResult = MutableLiveData<Boolean>()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun login(username: String, password: String) {
        launch {
            val database = HabitDatabase.getInstance(getApplication())

            val userDao = database.userDao()

            if (userDao.getUserCount() == 0) {
                val defaultUser = User(
                    username = "student",
                    password = "123"
                )

                userDao.insert(defaultUser)
            }

            val user = userDao.login(
                username,
                password
            )

            loginResult.postValue(user != null)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
