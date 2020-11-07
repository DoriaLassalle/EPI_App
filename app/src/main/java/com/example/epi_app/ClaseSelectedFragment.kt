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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.epi_app.model.local.AlumnoWithClases
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.model.local.PonyEntity
import com.example.epi_app.model.local.RelationAlumnoClase
import com.example.epi_app.model.network.Hit
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_clase_selected.*
import kotlinx.android.synthetic.main.fragment_clase_selected.*


class ClaseReservadaFragment : Fragment() {

    private val myViewModel: EpiViewModel by activityViewModels()
    lateinit var misClasesAdapter: ClaseSelectedAdapter
    private  var  userId:String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        misClasesAdapter= ClaseSelectedAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clase_selected, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        botPagarClase.setOnClickListener {
            Toast.makeText(context, "PROXIMAMENTE", Toast.LENGTH_LONG).show()
        }
        botClaseReservadaBack.setOnClickListener {
            findNavController().navigate(R.id.action_claseReservadaFragment_to_reservarClaseFragment)
        }


        val recyclerViewMisClases=recyclerMisClases
        recyclerViewMisClases.layoutManager=GridLayoutManager(context,2)
        recyclerViewMisClases.adapter=misClasesAdapter

                    //recibiendo el email del usuario
        myViewModel.selectedRecibir.observe(viewLifecycleOwner, Observer {
            Log.d("Que hay?", it)
            userId=it

        })
        myViewModel.getAlumnoWithClase().observe(viewLifecycleOwner, Observer {
            misClasesAdapter.UpdateAdapter(convertListToShow(it))


        })

    }

    fun convertListToShow(list: List<AlumnoWithClases>) : List<ClaseEntity> {
        val  listMutable= mutableListOf<ClaseEntity>()
        list.map {

            listMutable.add(decodingArray(it.clasesConAlumnos))
        }
        return listMutable
    }
    fun decodingArray(list2: List<ClaseEntity>): ClaseEntity{
       lateinit var objeto:ClaseEntity
        list2.map {
            objeto=it
        }
        return objeto

    }
}