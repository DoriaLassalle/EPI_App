package com.example.epi_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_recuperar.*

class RecuperarFragment : Fragment() {

    private val myViewModel: EpiViewModel by activityViewModels()
    lateinit var toUpdate:AlumnoEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recuperar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* botCambiarPassword.setOnClickListener {

            myViewModel.validateMail(recupMailRegist.text.toString()).observe(viewLifecycleOwner,
            Observer {
                if (recupMailRegist==null){

                        Toast.makeText(context, "SU CORREO NO HA SIDO REGISTRADO", Toast.LENGTH_LONG).show()

                }else{
                    toUpdate=(AlumnoEntity(name = it.name, lastName = it.lastName, email = it.email,
                        password = chgPass.text.toString(), age = it.age, apoderado = it.apoderado,
                    experience = it.experience))

                    myViewModel.insert(toUpdate)


                    Toast.makeText(context, "CONTRASEÑA ACTUALIZADA :)", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_recuperarFragment_to_LoginFragment)

                }
            })



        }*/
    }
}