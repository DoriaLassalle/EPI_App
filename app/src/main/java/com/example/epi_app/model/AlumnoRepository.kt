package com.example.epi_app.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.local.AlumnoDao
import com.example.epi_app.model.local.PonyDao
import com.example.epi_app.model.local.PonyEntity
import com.example.epi_app.model.network.Hit
import com.example.epi_app.model.network.PonyRetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AlumnoRepository (private val myAlumnoDao: AlumnoDao, private val myPonyDao: PonyDao){ //instacio el dao

    private val mRetrofit= PonyRetrofitClient.retrofitInstance()
    val key=Pair("key", "18827506-60fd5dd04e7ef2793d6e76d76")
    val param= Pair("q", "pony")
    val map = mapOf(key, param)

    val allAlumnosLiveData=myAlumnoDao.getAllAlumnos()   //live data con  lo de la tabla


    fun insertStudents(student: AlumnoEntity)= CoroutineScope(Dispatchers.IO).launch {
       myAlumnoDao.insertAlumno(student)
    }
    fun getAlumnos()= CoroutineScope(Dispatchers.IO).launch {
        myAlumnoDao.getAllAlumnos()
    }

    fun validateUser(correo:String, contras:String): LiveData<AlumnoEntity>{
        return myAlumnoDao.validateUser(correo, contras)
    }
    fun getPonyPhotoFromApi() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { mRetrofit.fetchAllPony(map) }
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let {
                    val data = convertPonyList(it.hits)

                    myPonyDao.insertListPony(data)
                }
                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }
        service.onFailure {
            Log.e("ERROR", it.message.toString())
        }
    }

    fun convertPonyList(list: List<Hit>) : List<PonyEntity> {
        val listMutable = mutableListOf<PonyEntity>()
        list.map {
            listMutable.add(PonyEntity(it.largeImageURL, it.likes.toString()))
        }
        return listMutable
    }

    

}