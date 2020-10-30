package com.example.epi_app.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ClaseDao {

    @Insert
    fun insertClases()

    @Query("SELECT * FROM clase")
    fun getAllClases()

    @Query("SELECT cupos FROM clase")
    fun getCupos()



}