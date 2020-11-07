package com.example.epi_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.Observer


class LoginFragment : Fragment() {

    val myViewModel: EpiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegistrar.setOnClickListener {

            findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
        }

        btnIngresar.setOnClickListener {
            if (loginMail.text.isBlank() || loginPassword.text.isBlank()) {

                Toast.makeText(context, "COMPLETA AMBOS CAMPOS", Toast.LENGTH_LONG).show()

            }else if (!loginMail.text.contains("@")){

                    Toast.makeText(context, "FORMATO DE CORREO INCORRECTO", Toast.LENGTH_LONG).show()

            } else {
                                    //envio el correo y passs ingresados a la bd para validar que existan
                myViewModel.validateUser(loginMail.text.toString(), loginPassword.text.toString())
                    .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                        if (it == null) {
                            Toast.makeText(context, "DATOS USUARIO INVÁLIDOS", Toast.LENGTH_LONG)
                                .show()
                        } else {

                            myViewModel.select(it.email)        //traspado el mail-pk
                            myViewModel.selectName(it.name)  //traspaso el nombre para la bievenida



                            findNavController().navigate(R.id.action_LoginFragment_to_HomeFragment)

                                Toast.makeText(context, "HOLA ${it.name} :)", Toast.LENGTH_LONG).show()

                        }
                    })
            }

        }

        recuperar.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_recuperarFragment)
        }
    }
}