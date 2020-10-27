package com.example.epi_app.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class AlumnoEntity (


    @PrimaryKey val email:String,
    val name:String,
    val lastName:String,
    val age:Int,
    val password:String,
    val apoderado:String,
    val experience: Boolean)