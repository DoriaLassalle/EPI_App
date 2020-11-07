package com.example.epi_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_admin_ver_clases.*

class AdminVerClasesFragment : Fragment() {

    private val myViewModel: EpiViewModel by activityViewModels()
    lateinit var myAdpater6: AdminVerClasesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAdpater6= AdminVerClasesAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_ver_clases, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        botAdminBack2.setOnClickListener {
            findNavController().navigate(R.id.action_adminClaseCnAlumnosFragment_to_adminClaseFragment)
        }

        val otroRecycler=recyclerAdminVerClases
        otroRecycler.layoutManager=LinearLayoutManager(context)
        otroRecycler.adapter=myAdpater6

        myViewModel.getAllClases().observe(viewLifecycleOwner, Observer {
            myAdpater6.updteAdpater(it)
        })



    }
}