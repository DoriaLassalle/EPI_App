package com.example.epi_app.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ClaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClase(clase:ClaseEntity)

    @Query("SELECT * FROM clase")
    fun getAllClases():LiveData<List<ClaseEntity>>





}