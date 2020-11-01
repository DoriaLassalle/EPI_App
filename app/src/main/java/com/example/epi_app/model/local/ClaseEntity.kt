package com.example.epi_app.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "clase")
data class ClaseEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val dia: String,
    val hora: String,
    val profesor: String,
    val cupos:String,       //val cupos: ArrayList<String>
    val nivel: String,

    //val nombreAlumno: AlumnoEntity   ForeignKey?
)

@Entity
data class Profesor(
    @PrimaryKey
    val nombreProfe: String
)