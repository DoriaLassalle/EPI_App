package com.example.epi_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.viewmodel.AlumnoViewModel
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    private val myViewModel: AlumnoViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       btnGuardarDatos.setOnClickListener {
           if (regNombre.text.isBlank()|| regApellido.text.isBlank()|| regEdad.text.isBlank()||
                   regMail.text.isBlank()|| regPass.text.isBlank()|| regPass2.text.isBlank()) {

               Toast.makeText(context, "COMPLETA TODOS LOS CAMPOS", Toast.LENGTH_LONG).show()

           }else if(!regPass.text.toString().equals(regPass2.text.toString())){

                   Toast.makeText(context, "LAS CONTRASEÑAS NO COINCIDEN", Toast.LENGTH_LONG).show()

           }else{
               var exp=false
                if(checkBox.isChecked)  {
                    exp=true

                }
               myViewModel.insert(
                   AlumnoEntity(email = "${regMail.text}", name="${regNombre.text}",
                   lastName = "${regApellido.text}", age = regEdad.text.toString().toInt(),
                   password = "${regPass.text}", apoderado = "${regApoderado.text}",
                   experience = exp )
               )
               Log.d("registro", it.toString())

                   Toast.makeText(context, "USUARIO REGISTRADO :)", Toast.LENGTH_LONG).show()

               findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)

           }
       }
    }
}