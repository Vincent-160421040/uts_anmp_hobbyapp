package com.dimas.uts_anmp_hobbyapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE iduser = :id")
    fun selectUser(id: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE iduser = :id AND password = :password")
    fun loginUser(id: String, password: String): User?

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user:User)
}