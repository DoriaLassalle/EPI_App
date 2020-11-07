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

    @Query ("SElECT * FROM AlumnoEntity WHERE email=:correo AND password=:contras")
    fun validateUser(correo: String, contras: String): LiveData<AlumnoEntity>

    @Query ("SElECT * FROM AlumnoEntity WHERE email=:correoElect")
    fun validateMail(correoElect:String):LiveData<AlumnoEntity>



    @Query("UPDATE alumnoentity SET password = :newPass WHERE email = :emailUser")
    fun insertNewPassword(newPass: String, emailUser: String?): Int





}