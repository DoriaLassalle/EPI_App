package com.example.epi_app.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ClaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClase(clase: ClaseEntity)

    @Query("SELECT * FROM clase")
    fun getAllClases():LiveData<List<ClaseEntity>>

   /* @Transaction
    @Query("SELECT * FROM AlumnoEntity")
    fun getAlumnoWithClase(): LiveData<List<RelationAlumnoClase>>*/

    @Transaction
    @Query ("SELECT * FROM AlumnoEntity")
    fun getAlumnoWithClase():LiveData<List<AlumnoWithClases>>

    @Transaction
    @Query ("SELECT * FROM clase")
    fun getClasesConAlumnos ():LiveData<List<ClaseWithAlumnos>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRelation(objeto: RelationAlumnoClase)


   /* @Query("UPDATE clase SET alumnoEmailId = :idEmail WHERE id = :id")
    fun insertIdAlumnoClase(id: Int, idEmail: String?): Int*/


}