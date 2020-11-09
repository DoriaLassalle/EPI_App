package com.example.epi_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.epi_app.R
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_admin_class_alum.*


class AdminClassAlumFragment : Fragment() {

    private val myViewmodel:EpiViewModel by activityViewModels()

    lateinit var misAlumnosAdapter: AdminClassAlumAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        misAlumnosAdapter= AdminClassAlumAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_class_alum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        botAdminBack3.setOnClickListener {
            findNavController().navigate(R.id.action_adminClassAlumFragment_to_adminClaseCnAlumnosFragment)
        }
    }
}