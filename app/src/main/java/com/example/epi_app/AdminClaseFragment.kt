package com.example.epi_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_admin.*
import kotlinx.android.synthetic.main.fragment_admin_clase.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlin.concurrent.fixedRateTimer


class AdminClaseFragment : Fragment() {

    private val myViewModel :EpiViewModel by activityViewModels()
    var diaIngresado:Int=0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_clase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        datePickerAdmin.setOnClickListener { showDatePickerDialog() }



        botAdminBack.setOnClickListener {
            findNavController().navigate(R.id.action_adminClaseFragment_to_adminFragment)
        }

        botGuardarClase.setOnClickListener {
            if (horaClase.text.isBlank()||profeClase.text.isBlank()|| caballosClase.text.isBlank() ||
                    nivelClase.text.isBlank()||datePickerAdmin.text.isBlank()){

                    Toast.makeText(context, "COMPLETA TODA LA INFORMACIÓN", Toast.LENGTH_LONG).show()
            }else {

                myViewModel.insertClass(
                    ClaseEntity(
                        dia = diaIngresado.toString(),
                        hora = horaClase.text.toString(),
                        profesor = profeClase.text.toString(),
                        cupos = caballosClase.text.toString(),
                        nivel = nivelClase.text.toString()
                    )
                )


                Toast.makeText(context, "CLASE GUARDADA :)", Toast.LENGTH_LONG).show()

                horaClase.setText("")
                datePickerAdmin.setText("")
            }
        }
    }
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        activity?.supportFragmentManager?.let { datePicker.show(it, "datePicker") }
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        diaIngresado=day
        datePickerAdmin.setText("Clase para el: $day/$month/$year")
    }
}