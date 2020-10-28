package com.example.epi_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.local.AlumnoDataBase.Companion.getDataBase
import com.example.epi_app.model.AlumnoRepository

class AlumnoViewModel (application: Application): AndroidViewModel(application){

   private val myRepository: AlumnoRepository

    val allAlumno: LiveData<List<AlumnoEntity>>


    init {
        val dao= getDataBase(application).alumnoDao()
        val dao2= getDataBase(application).ponyDao()
        myRepository= AlumnoRepository(dao, dao2)  //comunico dao con repository

        allAlumno=myRepository.allAlumnosLiveData
    }



    fun insert(student: AlumnoEntity){
        myRepository.insertStudents(student)
    }

    fun getAlumnos(){
         myRepository.getAlumnos()
    }
    fun validateUser(correo:String, contras:String): LiveData<AlumnoEntity>{
        return myRepository.validateUser(correo, contras)
    }



}