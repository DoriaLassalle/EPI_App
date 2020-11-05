package com.example.epi_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_clase_selected.*
import kotlinx.android.synthetic.main.fragment_clase_selected.*


class ClaseReservadaFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    }
}