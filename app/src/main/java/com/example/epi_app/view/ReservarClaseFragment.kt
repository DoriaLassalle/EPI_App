package com.example.epi_app.view

import android.app.AlertDialog
import android.content.DialogInterface
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
import com.example.epi_app.R
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.model.local.RelationAlumnoClase
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_reservar_clase.*


class ReservarClaseFragment : Fragment(), ReservarClaseAdapter.PassData {

    private val myViewModel:EpiViewModel by activityViewModels()
    lateinit var myAdapterReservar: ReservarClaseAdapter
    lateinit var fechaClaseElegida:String
    lateinit var horaClaseElegida:String
    lateinit var idAlumnoEmail:String
    private var cuposDisponibles:Int=0
    private var idClaseElegida:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAdapterReservar= ReservarClaseAdapter(this)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservar_clase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myRecycler=recyclerLoQueHay
        myRecycler.layoutManager=LinearLayoutManager(context)
        myRecycler.adapter=myAdapterReservar

                //muestro las clases disponibles -que ingresó el admin
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

    override fun passClaseInfo(claseInfo: ClaseEntity) {       //tengo la clase que seleccionó user y sus datos
        myViewModel.classSelect(claseInfo)                      //envio al viewmodel
        Log.d("clase eleg", claseInfo.toString())

        fechaClaseElegida=claseInfo.dia
        horaClaseElegida=claseInfo.hora
        idClaseElegida = claseInfo.id                //extraigo el id(PK) de la clase selected
        cuposDisponibles = claseInfo.cupos.toInt()

        //Traigo el alumno que ingresó a la app por su PK que es el mail-picked at loginfrag
        myViewModel.selectedRecibir.observe(viewLifecycleOwner, Observer {
            idAlumnoEmail = it
            Log.d("content", it)
        })

        if (cuposDisponibles == 0) {
            Toast.makeText(context, ":( ESTA CLASE YA NO TIENE CUPOS", Toast.LENGTH_LONG).show()

        } else {

            val managerDialog2: AlertDialog? = activity?.let {
                val builder2 = AlertDialog.Builder(it)
                builder2.apply {
                    setPositiveButton(
                        R.string.reserv_clase,
                        DialogInterface.OnClickListener { dialog, id ->

                            //relaciono la clase elegida al alumno, envío a BD
                            myViewModel.carrito(RelationAlumnoClase
                                (email = idAlumnoEmail, id = idClaseElegida))

                            //envio los cupos y el id de la clase to update los cupos
                            myViewModel.updateCupos(cuposDisponibles, idClaseElegida)

                            Toast.makeText(context, "CLASE N° ${claseInfo.id} AGREGADA",
                                Toast.LENGTH_SHORT).show()

                        })
                    setNegativeButton(
                        R.string.cancelar2,
                        DialogInterface.OnClickListener { dialog, id ->
                        })
                }
                builder2.setTitle(R.string.titulo_dialog2)
                builder2.setMessage("Deseas agregar la clase N° $idClaseElegida para el día: $fechaClaseElegida" +
                        " a las $horaClaseElegida horas?")
                builder2.create()
                builder2.show()
            }

        }//FALTA LIMITAR EL CLICK ENLA CLASE PARA QUE NO LA SELECCIONE 2 VECES, YA QUE DESCUENTA OTRO CUPO
    }

}