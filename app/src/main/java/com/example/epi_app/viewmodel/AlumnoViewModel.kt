package com.example.epi_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.local.AlumnoDataBase.Companion.getDataBase
import com.example.epi_app.model.AlumnoRepository

class AlumnoViewModel (application: Application): AndroidViewModel(application){

   private val myAlumnoRepository: AlumnoRepository

    val allAlumno: LiveData<List<AlumnoEntity>>


    init {
        val dao= getDataBase(application).alumnoDao()
        myAlumnoRepository= AlumnoRepository(dao)    //comunico dao con repository
        allAlumno=myAlumnoRepository.allAlumnosLiveData
    }

    fun insert(student: AlumnoEntity){
        myAlumnoRepository.insertStudents(student)
    }

    fun getAlumnos(){
         myAlumnoRepository.getAlumnos()
    }



}