package com.example.epi_app.model


import androidx.lifecycle.LiveData
import com.example.epi_app.R
import com.example.epi_app.model.local.*
import com.example.epi_app.model.netw.PonyApi
import com.example.epi_app.model.network.Hit
import com.example.epi_app.model.network.Pony
import com.example.epi_app.model.network.PonyRetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback


class EpiRepository (private val myAlumnoDao: AlumnoDao, private val myPonyDao: PonyDao,
    private  val myClaseDao: ClaseDao){ //instacio el dao

    private val mRetrofit= PonyRetrofitClient.retrofitInstance()

    val allAlumnosLiveData=myAlumnoDao.getAllAlumnos()   //live data con  lo de la tabla

    fun insertStudents(student: AlumnoEntity)= CoroutineScope(Dispatchers.IO).launch {
       myAlumnoDao.insertAlumno(student)
    }

    fun insertClass(clase: ClaseEntity)= CoroutineScope(Dispatchers.IO).launch {
        myClaseDao.insertClase(clase)
    }

    fun getAlumnos()= CoroutineScope(Dispatchers.IO).launch {
        myAlumnoDao.getAllAlumnos()
    }

    fun validateUser(correo:String, contras:String): LiveData<AlumnoEntity>{
        return myAlumnoDao.validateUser(correo, contras)
    }

    fun validateMail(mail:String): LiveData<AlumnoEntity>{
        return myAlumnoDao.validateMail(mail)
    }

    fun getAllPony(): LiveData<List<PonyEntity>>{
        return myPonyDao.getAllPony()
    }

    fun getPonyPhotoFromApi() = CoroutineScope(Dispatchers.IO).launch {

        val key=Pair("key", "18827506-60fd5dd04e7ef2793d6e76d76")
        val param= Pair("q", "pony")                    //aqui le pasamos  el tag
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

        //convertir lista que llega de inet que es de Hit, a una lista de objs PonyEntity que tiene 3 parametros:
    fun convertPonyList(list: List<Hit>) : List<PonyEntity> {
           val  listMutable= mutableListOf<PonyEntity>()
        list.map {
            listMutable.add(PonyEntity(it.id, it.largeImageURL, it.likes.toString())) //le decimos cuales son los parametros
        }
        return listMutable
    }

    fun getDataTeam():List<Team> {
        val teamList:ArrayList<Team> = ArrayList()

        teamList.add(
            Team("SHARON LASSALLE C.", "Master Profe", R.drawable.sha, "Equitadora de no se cuando," +
                " Domadora de potros chúcaros y tierna profe de niñitos ")
        )
        teamList.add(Team("TOMÁS VAN CAUWELAERT", "Profe", R.drawable.tom, "bla bla"))
        teamList.add(Team("JEAN-PIERRE LASSALLE T.", "Profe", R.drawable.jp, "bla bla bla"))

        return teamList

    }

    fun getDataGallery():List<Int>{
        val galleryList = listOf<Int>(R.drawable.gal1, R.drawable.gal2, R.drawable.gal3, R.drawable.gal4,
        R.drawable.gal5, R.drawable.gal6, R.drawable.gal7, R.drawable.gal8, R.drawable.gal9, R.drawable.gal10)

        return galleryList
    }

    fun getNivelSpinnerData():List<String>{
        val nivelList= listOf<String>("Baby Pony", "Prebásico", "Básico", "Intermedio Avanzado" )
        return nivelList
    }



}