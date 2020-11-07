package com.example.epi_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.model.local.RelationAlumnoClase
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_reservar_clase.*


class ReservarClaseFragment : Fragment(), ReservarClaseAdapter.PassData{

    private val myViewModel:EpiViewModel by activityViewModels()
    lateinit var myAdapterReservar: ReservarClaseAdapter
    lateinit var idAlumnoEmail:String
    private var idClaseElegida:Int=0

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

         idClaseElegida=claseInfo.id                 //extraigo el id(PK) de la clase selected



                    //Traigo el alumno que ingresó a la app por su PK que es el mail-picked at loginfrag
        myViewModel.selectedRecibir.observe(viewLifecycleOwner, Observer {
           idAlumnoEmail=it
            Log.d("content", it)

        })
                     //Valido que la clase sea seleccionada una sola vez
      /* if (si no ha pinchadola clase entonces la guardo) {

            Toast.makeText(context, "CLASE N° ${claseInfo.id} SELECCIONADA", Toast.LENGTH_LONG)
                .show()



        }else{
                Toast.makeText(context, "YA SELECCIONASTE ESTA CLASE", Toast.LENGTH_LONG).show()
        }*/

        //relaciono la clase elegida al alumno, envio a BD
        myViewModel.carrito(RelationAlumnoClase(email = idAlumnoEmail, id = idClaseElegida))
    }
}