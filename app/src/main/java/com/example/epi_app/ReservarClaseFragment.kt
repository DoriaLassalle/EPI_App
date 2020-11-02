package com.example.epi_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.EntityDeletionOrUpdateAdapter
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_admin_clase.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_reservar_clase.*


class ReservarClaseFragment : Fragment(), ClaseAdapter.PassData{

    private val myViewModel:EpiViewModel by activityViewModels()
    lateinit var myAdapter: ClaseAdapter
    lateinit var alumno:AlumnoEntity
   lateinit var idAlumnoEmail:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAdapter= ClaseAdapter(this)

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
        myRecycler.adapter=myAdapter

                //muestro las clases disponibles que ingresó el admin
        myViewModel.getAllClases().observe(viewLifecycleOwner, Observer {
        myAdapter.UpdateAdapter(it)

        })

        myViewModel.getAlumnoWithClase().observe(viewLifecycleOwner, Observer {
            //myAdapter.UpdateAdapter(it)

            Log.d("alumnocnclase", it.toString())

        })



        botPagarClase.setOnClickListener {
                Toast.makeText(context, "OPCIÓN NO HABILITADA AÚN :(", Toast.LENGTH_LONG).show()
        }

        botClaseHome.setOnClickListener {
            findNavController().navigate(R.id.action_reservarClaseFragment_to_HomeFragment)
        }
    }

    override fun passClaseInfo(claseInfo: ClaseEntity){
       // myViewModel.classSelect(claseInfo)

        myViewModel.selectedRecibir.observe(viewLifecycleOwner, Observer {
            idAlumnoEmail=it

        })

            Toast.makeText(context, "clase selected", Toast.LENGTH_LONG).show()

        val id=ClaseEntity(alumnoEmailId = idAlumnoEmail)

    }
}