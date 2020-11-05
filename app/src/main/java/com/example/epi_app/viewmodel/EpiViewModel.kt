package com.example.epi_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.epi_app.model.local.EpiPony
import com.example.epi_app.model.local.AlumnoDataBase.Companion.getDataBase
import com.example.epi_app.model.EpiRepository
import com.example.epi_app.model.local.*

class EpiViewModel (application: Application): AndroidViewModel(application){

   private val myRepository: EpiRepository

    val allAlumno: LiveData<List<AlumnoEntity>>
    val classSelected = MutableLiveData<ClaseEntity>()   //clase seleccionado
    val ponyFaceSelected=MutableLiveData<EpiPony>()
    val selectedRecibir = MutableLiveData<String>()
    val selectedName=MutableLiveData<String>()



    init {
        val dao= getDataBase(application).alumnoDao()
        val dao2= getDataBase(application).ponyDao()
        val dao3= getDataBase(application).claseDao()
        myRepository= EpiRepository(dao, dao2, dao3)  //comunico dao con repository

        allAlumno=myRepository.allAlumnosLiveData
    }

    fun classSelect(claseSel: ClaseEntity) {
        classSelected.value = claseSel
    }

    fun ponyFaceSelect(horse: EpiPony){
        ponyFaceSelected.value =horse
    }

    fun select(item: String) {
        selectedRecibir.value = item
    }
    fun selectName(name:String){
        selectedName.value=name
    }

    fun insert(student: AlumnoEntity){
        myRepository.insertStudents(student)
    }

    fun insertClass(clase :ClaseEntity){
        myRepository.insertClass(clase)
    }

    fun getAlumnos(){
         myRepository.getAlumnos()
    }

    fun getAllClases():LiveData<List<ClaseEntity>>{
        return myRepository.getAllClases()
    }

    fun getAlumnoWithClase():LiveData<List<RelationAlumnoClase>>{
        return myRepository.getAlumnoWithClase()
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

    fun getDataTienda():List<Tienda>{
        return myRepository.getDataTienda()

    }

    fun getDataHorse():List<EpiPony> {
        return myRepository.getDataHorse()

    }

    fun carrito(idClase: Int, idEmail: String){
        myRepository.carrito(idClase, idEmail)
    }





}