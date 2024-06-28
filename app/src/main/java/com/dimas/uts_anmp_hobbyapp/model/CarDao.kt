package com.dimas.uts_anmp_hobbyapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarDao {
    @Query("SELECT * FROM car")
    fun selectAllCar(): List<Car>

    @Query("SELECT * FROM car WHERE id = :id")
    fun selectCar(id: Int): Car?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(car: Car)

    @Update
    fun updateUser(car: Car)

    @Delete
    fun deleteUser(car: Car)
}