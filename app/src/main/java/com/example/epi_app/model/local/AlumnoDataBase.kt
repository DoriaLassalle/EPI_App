package com.example.epi_app.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlumnoEntity::class, PonyEntity::class, ClaseEntity::class,
    RelationAlumnoClase::class],  version = 1)

 abstract class AlumnoDataBase: RoomDatabase() {

    abstract fun alumnoDao(): AlumnoDao
    abstract fun ponyDao():PonyDao
    abstract fun claseDao():ClaseDao

    companion object {
        @Volatile
        private var INSTANCE: AlumnoDataBase?=null

        fun getDataBase(context: Context): AlumnoDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context, AlumnoDataBase::class.java,
                    "sept_db").build()
                INSTANCE = instance
                return instance
            }

        }

    }

}