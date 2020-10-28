package com.example.epi_app

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.epi_app.model.AlumnoRepository
import com.example.epi_app.viewmodel.AlumnoViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*


class LoginFragment : Fragment() {

    val myViewModel: AlumnoViewModel by activityViewModels()

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
          /*  if (loginMail.text.isBlank() || loginPassword.text.isBlank()) {

                Toast.makeText(context, "COMPLETA AMBOS CAMPOS", Toast.LENGTH_LONG).show()

            } else {

                myViewModel.validateUser(loginMail.text.toString(), loginPassword.text.toString())
                    .observe(viewLifecycleOwner, Observer {
                        if (it == null) {
                            Toast.makeText(context, "DATOS USUARIO INVÁLIDOS", Toast.LENGTH_LONG)
                                .show()
                        } else {

                            Toast.makeText(context,"BIENVENIDO ${it.name}" ,
                                Toast.LENGTH_LONG).show()


                        }
                    })
            }*/
            findNavController().navigate(R.id.action_LoginFragment_to_HomeFragment)
        }
    }
}