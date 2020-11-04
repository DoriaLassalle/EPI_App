package com.example.epi_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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


class AdminClaseFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val myViewModel :EpiViewModel by activityViewModels()
    var diaIngresado:Int=0
    var mesIngresado:Int=0
    lateinit var level:String


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

                // crear ArrayAdapter con el string array y el default spinner layout
        context?.let {
            ArrayAdapter.createFromResource(
                it, R.array.level_list,
                android.R.layout.simple_spinner_item).also { adapter ->
                //  layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // paso el adapter al spinner
                spinnerAdmminNivel.adapter = adapter
                spinnerAdmminNivel.onItemSelectedListener= this
            }
        }

        botGuardarClase.setOnClickListener {
            if (horaClase.text.isBlank()||profeClase.text.isBlank()|| caballosClase.text.isBlank() ||
                    datePickerAdmin.text.isBlank()){

                    Toast.makeText(context, "COMPLETA TODA LA INFORMACIÓN", Toast.LENGTH_LONG).show()
            }else {

                myViewModel.insertClass(
                    ClaseEntity(
                        dia = ("${diaIngresado}/${mesIngresado}"),
                        hora = horaClase.text.toString(),
                        profesor = profeClase.text.toString(),
                        cupos = caballosClase.text.toString(),
                        nivel = level
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
        mesIngresado=month
        datePickerAdmin.setText("Clase para el: $day/${month+1}/$year")  //+1 porque jan es 0
    }
    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        level=parent?.getItemAtPosition(p2) as String
        Log.d("spinner", level)

    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
            Toast.makeText(context, "NO SELECCIONASTE NIVEL", Toast.LENGTH_LONG).show()
    }


}