package com.example.epi_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.epi_app.R
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_recuperar.*

class RecuperarFragment : Fragment() {

    private val myViewModel: EpiViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recuperar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       botCambiarPassword.setOnClickListener {

           if (recupMailRegist.text.isBlank()||chgPass.text.isBlank()||chgPass2.text.isBlank()) {

                    Toast.makeText(context, "COMPLETA TODOS LOS CAMPOS", Toast.LENGTH_LONG).show()

                }else if(!chgPass.text.toString().equals(chgPass2.text.toString())){

                        Toast.makeText(context, "LAS CONTRASEÑAS NO COINCIDEN", Toast.LENGTH_LONG).show()

                }else{
                                //valido que el mail de usuario exista en la bd
                    myViewModel.validateMail(recupMailRegist.text.toString()).observe(viewLifecycleOwner,
                    Observer {

                        if (it==null){

                                Toast.makeText(context, "USUARIO NO REGISTRADO EN LA BASE",
                                    Toast.LENGTH_LONG).show()

                        }else {

                             val newPassword=chgPass.text.toString()
                             val emailUser=it.email

                             myViewModel.insertNewPassword(newPassword, emailUser)  //envio la clave nueva para actualizarla

                            Toast.makeText(context, "CONTRASEÑA ACTUALIZADA :)", Toast.LENGTH_LONG).show()
                            findNavController().navigate(R.id.action_recuperarFragment_to_LoginFragment)

                        }
                    })
                }

       }
    }
}