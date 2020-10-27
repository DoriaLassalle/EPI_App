package com.example.epi_app.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlumnoDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAlumno(person: AlumnoEntity)

    @Query ("SELECT * FROM AlumnoEntity")
    fun getAllAlumnos(): LiveData<List<AlumnoEntity>>




}