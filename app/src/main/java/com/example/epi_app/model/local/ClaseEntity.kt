package com.example.epi_app.model.local

import androidx.room.*


@Entity(tableName = "clase")
data class ClaseEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val dia: String,
    val hora: String,
    val profesor: String,
    val cupos:String,       //val cupos: ArrayList<String>
    val nivel: String,
    val alumnoEmailId:String

)

{constructor(alumnoEmailId: String): this(0,"","","","","",alumnoEmailId)

    constructor(dia: String, hora: String, profesor: String, cupos: String, nivel: String): this(0,dia,
    hora, profesor, cupos, nivel,"")

}



data class RelationAlumnoClase(
    @Embedded val alumno: AlumnoEntity,
    @Relation(
        parentColumn = "email",
        entityColumn = "alumnoEmailId"
    )
    val claseAlumnoList:List<ClaseEntity>

)


@Entity
data class Profesor(
    @PrimaryKey
    val nombreProfe: String
)