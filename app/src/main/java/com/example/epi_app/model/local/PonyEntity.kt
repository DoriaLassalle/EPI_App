package com.example.epi_app.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.epi_app.model.network.Hit
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pony")
data class PonyEntity (
    @PrimaryKey
    val id: Int,
    val largeImageURL: String,
    val likes: String

)

