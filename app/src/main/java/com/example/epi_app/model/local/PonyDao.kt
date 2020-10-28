package com.example.epi_app.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PonyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListPony(ponyList: List<PonyEntity>)

    @Query("SELECT * FROM pony")
    fun getAllPony(): LiveData<List<PonyEntity>>
}