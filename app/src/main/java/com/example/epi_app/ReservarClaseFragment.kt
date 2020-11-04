package com.example.epi_app

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.clasedisponible_list.*
import kotlinx.android.synthetic.main.fragment_reservar_clase.*


class ReservarClaseFragment : Fragment(), ReservarClaseAdapter.PassData{

    private val myViewModel:EpiViewModel by activityViewModels()
    lateinit var myAdapterReservar: ReservarClaseAdapter
    lateinit var alumno:AlumnoEntity
   lateinit var idAlumnoEmail:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAdapterReservar= ReservarClaseAdapter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservar_clase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myRecycler=recyclerLoQueHay
        myRecycler.layoutManager=LinearLayoutManager(context)
        myRecycler.adapter=myAdapterReservar

                //muestro las clases disponibles que ingresó el admin
        myViewModel.getAllClases().observe(viewLifecycleOwner, Observer {
        myAdapterReservar.UpdateAdapter(it)

        })

        myViewModel.getAlumnoWithClase().observe(viewLifecycleOwner, Observer {
            //myAdapter.UpdateAdapter(it)

            Log.d("alumnocnclase", it.toString())

        })


        botClaseHome.setOnClickListener {
            findNavController().navigate(R.id.action_reservarClaseFragment_to_HomeFragment)
        }
        botVerMisClases.setOnClickListener {
            findNavController().navigate(R.id.action_reservarClaseFragment_to_claseReservadaFragment)
        }
    }

    override fun passClaseInfo(claseInfo: ClaseEntity){       //tengo la clase que selecciono y sus datos
        myViewModel.classSelect(claseInfo)                      //envio al viewmodel
        Log.d("clase eleg", claseInfo.toString())

        val idClaseElegida=claseInfo.id             //extraigo el id(PK) de la clase selected

                    //Traigo el alumno que está en la sesión por su PK
        myViewModel.selectedRecibir.observe(viewLifecycleOwner, Observer {
           idAlumnoEmail=it

        })
                     //Valido que la clase sea seleccionada una sola vez
        if (!claseInfo.alumnoEmailId.equals(idAlumnoEmail)) {

            Toast.makeText(context, "CLASE N° ${claseInfo.id} SELECCIONADA", Toast.LENGTH_LONG)
                .show()

            myViewModel.carrito(idClaseElegida, idAlumnoEmail) //relaciono la clase elegida al alumno, envio a BD

        }else{
                Toast.makeText(context, "YA SELECCIONASTE ESTA CLASE", Toast.LENGTH_LONG).show()
        }

    }
}