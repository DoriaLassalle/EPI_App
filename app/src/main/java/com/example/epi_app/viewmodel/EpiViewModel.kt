package com.example.epi_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.epi_app.model.local.Team
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.local.AlumnoDataBase.Companion.getDataBase
import com.example.epi_app.model.EpiRepository
import com.example.epi_app.model.local.PonyEntity

class EpiViewModel (application: Application): AndroidViewModel(application){

   private val myRepository: EpiRepository

    val allAlumno: LiveData<List<AlumnoEntity>>


    init {
        val dao= getDataBase(application).alumnoDao()
        val dao2= getDataBase(application).ponyDao()
        myRepository= EpiRepository(dao, dao2)  //comunico dao con repository

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
    fun validateMail(mail:String):LiveData<AlumnoEntity>{
        return myRepository.validateMail(mail)
    }

    fun getData(): LiveData<List<PonyEntity>>{
        myRepository.getPonyPhotoFromApi()  //llama a inet
        return myRepository.getAllPony()     //va a buscar a la bd
    }

    fun getDataTeam(): List<Team> {
        return myRepository.getDataTeam()

    }

    fun getDataGallery():List<Int> {
        return myRepository.getDataGallery()

    }





}