package com.example.epi_app.model


import android.util.Log
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide.init
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.local.AlumnoDao
import com.example.epi_app.model.local.PonyDao
import com.example.epi_app.model.local.PonyEntity
import com.example.epi_app.model.netw.PonyApi
import com.example.epi_app.model.network.Hit
import com.example.epi_app.model.network.Pony
import com.example.epi_app.model.network.PonyRetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback


class AlumnoRepository (private val myAlumnoDao: AlumnoDao, private val myPonyDao: PonyDao){ //instacio el dao

    private val mRetrofit= PonyRetrofitClient.retrofitInstance()

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

    fun getAllPony(): LiveData<List<PonyEntity>>{
        return myPonyDao.getAllPony()
    }

    fun getPonyPhotoFromApi() = CoroutineScope(Dispatchers.IO).launch {

        val key=Pair("key", "18827506-60fd5dd04e7ef2793d6e76d76")
        val param= Pair("q", "pony")  //aqui le pasamos lo queremos traer, el tag
        val map = mapOf(key, param)

        val instancia: PonyApi = PonyRetrofitClient.retrofitInstance()
        val call: Call<Pony>

        call=instancia.fetchAllPony(map)

        call.enqueue(object : Callback<Pony>{
            override fun onResponse(call: Call<Pony>, response: retrofit2.Response<Pony>) {

                val pony= response.body()
                if( pony != null) {

                    val data = convertPonyList(pony.hits)

                     CoroutineScope(Dispatchers.IO).launch{
                         myPonyDao.insertListPony(data)   //le pasamos la lista convertida
                    }
                }
            }

            override fun onFailure(call: Call<Pony>, t: Throwable) {

            }
        })

        /* service.onSuccess {
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
            Log.e("holi", it.message.toString())
        }*/
    }
        //convierte la lista que llega de inet que es Hit a una lista de ponyEntity que tiene 2 parametros
    fun convertPonyList(list: List<Hit>) : List<PonyEntity> {
        val listMutable = mutableListOf<PonyEntity>()
        list.map {
            listMutable.add(PonyEntity(it.largeImageURL, it.likes.toString())) //le decimos cuales son los parametros
        }
        return listMutable
    }

    

}