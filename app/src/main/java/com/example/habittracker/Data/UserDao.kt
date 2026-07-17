package com.example.habittracker.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habittracker.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query(
        " SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1"
    )
    fun login(username: String, password: String): User?

    @Query("SELECT COUNT(*) FROM users")
    fun getUserCount(): Int
}