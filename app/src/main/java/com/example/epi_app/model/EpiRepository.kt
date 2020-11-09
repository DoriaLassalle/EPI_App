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

    fun getClasesConAlumnos ():LiveData<List<ClaseWithAlumnos>>{
        return myClaseDao.getClasesConAlumnos()

    }
                            //para validar que el usuario existe en la bd
    fun validateUser(correo:String, contras:String): LiveData<AlumnoEntity>{
        return myAlumnoDao.validateUser(correo, contras)
    }
                            //para cgh de contraseña
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
            "Desde muy pequeño ligado a la naturaleza,\nel campo y los caballos. Mejor amigo \nde" +
                    " Cururo.\nActualmente termina su carrera de Ingeniero en Biotecnología, por eso sólo lo " +
                    "vemos los fines de semana."))
        teamList.add(
            Team("JORGE NEGRETE", "Ordenanza\nEn la EPI desde 2016", R.drawable.jorge,
        "Encargado de cuidar a nuestros Ponies y Caballos, tenerlos bien alimentados, limpios" +
                "\ny felices."))
        teamList.add(Team("DANIELA", "Especialista en Barefooting Equino", R.drawable.logoepi,
        "Mantiene los cascos de nuestros Ponies y Caballos sanos, bien despalmados, aplomados y " +
                "sin herraduras."))
        teamList.add(Team("CONSUELO BITTNER", "Veterinaria", R.drawable.logoepi, "Veterinaria" +
                " favorita de los Ponies y Caballos.\nLos ayuda y sana cada vez que tienen un problema, aunque" +
                " sea pequeño."))
        teamList.add(Team("DOMINIQUE SUNKO", "Veterinaria", R.drawable.logoepi, "Veterinaria " +
                "amiga de todos los Ponies\ny Caballos.\nEncargada de sus desparasitaciones , vacunas" +
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
            EpiPony(R.drawable.facehakan, "Hakán", "Llegó a nuestra familia en el " +
                    "verano del 2019, venía sólo por unos meses y se quedó para siempre. Le gusta " +
                    "saltar, es muy rápido\ny bueno para comer. Con alumnos nuevos es muy tranquilo" +
                    " y paciente.\nApto para todos los niveles.", R.drawable.fullhakan))
        ponyFaceList.add(
            EpiPony(R.drawable.facemilodon, "Milodón", "Él es nuestro crack! Caballo " +
                    "de transición para jinetes avanzados que, luego de aprender y avanzar en ponies," +
                    " pueden montar al rey de la casa. Le encanta saltar, es muy rápido,\nvaliente " +
                    "y franco.",  R.drawable.fullmilodon))
        ponyFaceList.add(
            EpiPony(
                R.drawable.facepapaya, "Papaya", "Tiene 5 años, nació en la Epi y es " +
                        "hija de Vainilla. Súper buena yegüita para\njinetes de nivel intermedio y" +
                        "avanzado.\nEl 2019 ganó el premio \"Binomio mejor presentado\" en el Nacional" +
                        " de Escuelas y ha ganado algunos primeros lugares en adiestramiento nivel " +
                        "escuela.", R.drawable.fullpapaya))
        ponyFaceList.add(
            EpiPony(R.drawable.faceragnar, "Ragnar", "Es el caballo favorito de todos" +
                    " aunque sólo\nlo montan los profes. Todos lo quieren \nporque siempre esta " +
                    "mirando a los niños que llegan y les pide zanhorias. Le gusta que le rasquen " +
                    "la frente y las orejas.\nSolo para jinetes nivel avanzado.",R.drawable.fullragnar))

       ponyFaceList.add(
            EpiPony(R.drawable.facemorenamia, "Morena", "Morena Mía es su nombre original de la Epi." +
                " Llegó en el verano del 2020 y no se fue más. Es una santa, muy tierna y  buen " +
                "caracter. Apta para todos los niveles.", R.drawable.fullmorenamia))

        ponyFaceList.add(
            EpiPony(R.drawable.facevainilla, "Vainilla", "Llegó a la Epi hace mucho años" +
                    " y venía con\nuna sorpresa...la Papaya. Es nuestra reina, tiene caracter fuerte " +
                    "como toda reina\npero tiene un corazón dulce y leal\ncomo buen caballo. " +
                    "Apta para jinetes nivel básico, intermedio y avanzado.", R.drawable.fullvainilla))

        ponyFaceList.add(
            EpiPony(R.drawable.facesalem, "Salem", "Es el abuelito de la familia.\nLlegó " +
                    "con 18 años a darle equilibrio a la manada. Es un amor, es un caballo paciente," +
                    " obediente hasta con los más pequeños. Apto para todos los niveles", R.drawable.fullsalem))

        ponyFaceList.add(
            EpiPony(R.drawable.facefrodo, "Frodo", "Es abuelito al igual que Salem, pero no sabemos" +
                    " con exactitud su edad. Está en la Epi desde 2014, es uno de los fundadores" +
                    " junto con Cururo. Tiene excelente carácter y es el encargado de recibir a los" +
                    " baby ponies hasta los 5 años.", R.drawable.fullfrodo))

        ponyFaceList.add(
            EpiPony(R.drawable.facecururo, "Cururo", "Fundador de la Epi con otros " +
                    "ponies que ya no están. Es el encargado de recibir a los niños nivel baby pony " +
                    "y enseñarles todo para que  pasen a ponies más grandes.", R.drawable.fullcururo))


        return ponyFaceList
    }

    fun carrito( objeto: RelationAlumnoClase)   = CoroutineScope(Dispatchers.IO).launch {
        myClaseDao.insertRelation(objeto)
    }
            //actualizar la contraseña
    fun insertNewPassword(newPass: String, emailUser: String?)= CoroutineScope(Dispatchers.IO).launch {
        myAlumnoDao.insertNewPassword(newPass, emailUser)
    }
            //traigo los cupos disponibles de la clase y le resto el que tomo el usuario, envio
            //to update los cupos restantes
    fun updateCupos(cuposDisponibles: Int, idClaseElegida:Int)= CoroutineScope(Dispatchers.IO).launch {
       val cuposRestantes=cuposDisponibles-1

        myClaseDao.updateCuposClases(cuposRestantes, idClaseElegida)
    }

    fun deleteClase(claseToDelete:Int)= CoroutineScope(Dispatchers.IO).launch{
        myClaseDao.deleteClase(claseToDelete)
    }





}