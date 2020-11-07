package com.example.epi_app.model


import androidx.lifecycle.LiveData
import com.example.epi_app.model.local.EpiPony
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

    fun getAllClases():LiveData<List<ClaseEntity>>{
        return myClaseDao.getAllClases()

    }

    fun getAlumnoWithClase():LiveData<List<AlumnoWithClases>>{
        return myClaseDao.getAlumnoWithClase()
    }

    fun validateUser(correo:String, contras:String): LiveData<AlumnoEntity>{
        return myAlumnoDao.validateUser(correo, contras)
    }

    fun validateMail(correoElec:String):LiveData<AlumnoEntity>{
        return myAlumnoDao.validateMail(correoElec)
    }


    fun getAllPony(): LiveData<List<PonyEntity>>{
        return myPonyDao.getAllPony()
    }

    fun getPonyPhotoFromApi() = CoroutineScope(Dispatchers.IO).launch {

        val key=Pair("key", "18827506-60fd5dd04e7ef2793d6e76d76")
        val param= Pair("q", "horse")                    //aqui le pasamos  el tag
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
            Team("SHARON LASSALLE C.", "Profesora Titular\nTodos los niveles", R.drawable.sha,
                "Jinete desde muy pequeña," +
                    "\nha conocido y pacticado Enduro, Salto y Adiestramiento, Prueba Completa,\nRinning, etc." +
                    "\nFue Masajista Equino y Amansadora\nde caballos.\nFundadora de la EPI y encargada de la " +
                    "administración de clases y horarios.\nCertificada como Profesora de Equitación Nivel Básico " +
                    "FECH.\nCertificación Internacional FEF,\nGalope Nivel 6.\nEn proceso de certificación Coach " +
                    "FEI."))
        teamList.add(Team("TOMÁS VAN CAUWELAERT", "Profesor Titular\nTodos los niveles", R.drawable.tom,
            "Jinete desde muy pequeño, su interés se inclina 100% a la rama de adiestramiento." +
                    "\nEn proceso de formación Coach FEI."))
        teamList.add(Team("JEAN-PIERRE LASSALLE T.", "Profesor Ayudante\n Desde 2016", R.drawable.jp,
            "Desde muy pequeño ligado a la naturaleza, el campo y los caballos.\nMejor amigo de" +
                    "Cururo.\nActualmente termina su carrera de Ingeniero en Biotecnología, por eso sólo lo " +
                    "vemos los fines de semana."))
        teamList.add(
            Team("JORGE NEGRETE", "Ordenanza\nEn la EPI desde 2016", R.drawable.jorge,
        "Encargado de cuidar a nuestros Ponies y Caballos, tenerlos bien alimentados, limpios" +
                "y felices."))
        teamList.add(Team("DANIELA", "Especialista en Barefooting Equino", R.drawable.logoepi,
        "Mantiene los cascos de nuestros Ponies y Caballos sanos, bien despalmados, aplomados y " +
                "sin herraduras."))
        teamList.add(Team("CONSUELO BITTNER", "Veterinaria", R.drawable.logoepi, "Veterinaria" +
                "favorita de los Ponies y Caballos.\nLos ayuda y sana cada vez que tienen un problema, aunque" +
                " sea pequeño."))
        teamList.add(Team("DOMINIQUE SUNKO", "Veterinaria", R.drawable.logoepi, "Veterinaria " +
                "amiga de todos los Ponies y Caballos.\nEncargada de sus desparasitaciones , vacunas" +
                " reglamentarias y vitaminas. Mantiene todos los carné y pasaportes equinos al día."))

        return teamList

    }

    fun getDataTienda():List<Tienda>{
        val tiendaList:ArrayList<Tienda> = ArrayList()

        tiendaList.add(Tienda(R.drawable.tcasco1, "CASCO AJUSTABLE NEGRO", 42000))
        tiendaList.add(Tienda(R.drawable.tcasco2, "CASCO PRO", 48000))
        tiendaList.add(Tienda(R.drawable.tmandilnegro, "MANDIL DE SALTO NEGRO", 27000))
        tiendaList.add(Tienda(R.drawable.tmandilrojo, "MANDIL DE SALTO ROJO", 27000))
        tiendaList.add(Tienda(R.drawable.tamigurumi, "AMIGURUMI LLAVERO", 8000))
        tiendaList.add(Tienda(R.drawable.tcinchaguatero, "CINCHA GUATERO", 170000))
        tiendaList.add(Tienda(R.drawable.tpolainanegra, "POLAINA ADULTO NEGRO", 49000))
        tiendaList.add(Tienda(R.drawable.tpolainarosa,"POLAINA NIÑA ROSA", 35000))
        tiendaList.add(Tienda(R.drawable.tglove, "GUANTES DE RENO", 25000))

        return tiendaList


    }

    fun getDataHorse():List<EpiPony>{
        val ponyFaceList:ArrayList<EpiPony> =ArrayList()

        ponyFaceList.add(
            EpiPony(R.drawable.facehakan, "Hakán", "caballito hop",
        R.drawable.fullhakan)
        )
        ponyFaceList.add(
            EpiPony(R.drawable.facemilodon, "Milodón", "Le encanta su cueva",
        R.drawable.fullmilodon)
        )
        ponyFaceList.add(
            EpiPony(R.drawable.facepapaya, "Papaya", "Dulce y jugosa",
            R.drawable.fullpapaya)
        )
        ponyFaceList.add(
            EpiPony(R.drawable.faceragnar, "Ragnar", "Pony vikingo",
        R.drawable.fullragnar)
        )

        return ponyFaceList
    }

    fun carrito( objeto: RelationAlumnoClase)   = CoroutineScope(Dispatchers.IO).launch {
        myClaseDao.insertRelation(objeto)
    }
    fun insertNewPassword(newPass: String, emailUser: String?)= CoroutineScope(Dispatchers.IO).launch {
        myAlumnoDao.insertNewPassword(newPass, emailUser)
    }







}