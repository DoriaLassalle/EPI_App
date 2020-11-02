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
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_admin_clase.*
import kotlinx.android.synthetic.main.fragment_reservar_clase.*


class ReservarClaseFragment : Fragment(), ClaseAdapter.PassData{

    private val myViewModel:EpiViewModel by activityViewModels()
    lateinit var myAdapter: ClaseAdapter

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

        myViewModel.getAllClases().observe(viewLifecycleOwner, Observer {
        myAdapter.UpdateAdapter(it)

        })

        botPagarClase.setOnClickListener {
                Toast.makeText(context, "OPCIÓN NO HABILITADA AÚN :(", Toast.LENGTH_LONG).show()
        }

        botClaseHome.setOnClickListener {
            findNavController().navigate(R.id.action_reservarClaseFragment_to_HomeFragment)
        }
    }

    override fun passClaseInfo(claseInfo: ClaseEntity){
        myViewModel.classSelect(claseInfo)
        Log.d("clase sel", claseInfo.toString())

    }
}