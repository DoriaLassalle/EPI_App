package com.example.epi_app.model

import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.local.AlumnoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AlumnoRepository (private val myAlumnoDao: AlumnoDao){ //instacio el dao

    val allAlumnosLiveData=myAlumnoDao.getAllAlumnos()   //live data con  lo de la tabla

    fun insertStudents(student: AlumnoEntity)= CoroutineScope(Dispatchers.IO).launch {
       myAlumnoDao.insertAlumno(student)
    }
    fun getAlumnos()= CoroutineScope(Dispatchers.IO).launch {
        myAlumnoDao.getAllAlumnos()
    }

}