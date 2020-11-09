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
    //val alumnoEmailId:String
)

{constructor(alumnoEmailId: String): this(0,"","","","",""/*;alumnoEmailId*/)

    constructor(dia: String, hora: String, profesor: String, cupos: String, nivel: String): this(0,dia,
    hora, profesor, cupos, nivel)
}

@Entity(primaryKeys = ["email", "id"])
data class RelationAlumnoClase(
    val email:String,
    val id: Int
)

data class AlumnoWithClases(
    @Embedded val alumno:AlumnoEntity,
    @Relation(
        parentColumn = "email",
        entityColumn = "id",
        associateBy = Junction(RelationAlumnoClase::class)
    )
    val clasesConAlumnos:List<ClaseEntity>
)


data class ClaseWithAlumnos(
    @Embedded val clase:ClaseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "email",
        associateBy = Junction(RelationAlumnoClase::class)
    )
    val alumnosConClase:List<AlumnoEntity>
)

