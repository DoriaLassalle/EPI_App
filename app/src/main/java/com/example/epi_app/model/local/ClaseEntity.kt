package com.example.epi_app.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "clase")
data class ClaseEntity (
    @PrimaryKey
    val id:Int,
    val dia: String,
    val hora: String,
    val profesor: String,
    val cupos: Int,
    val categoria: String
)

@Entity
data class Profesor(
    @PrimaryKey
    val nombreProfe: String
)