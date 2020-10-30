package com.example.epi_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_team.*


class HomeFragment : Fragment() {

    private val myViewModel: EpiViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*myViewModel.validateUser(loginMail.text.toString(), loginPassword.text.toString())
            .observe(viewLifecycleOwner, Observer {
                welcome.text=("HOLA ${it.name} ${it.lastName} :)")
            })*/

        cvGallery.setOnClickListener {

            findNavController().navigate((R.id.action_HomeFragment_to_galeryFragment))
        }


        cvPony.setOnClickListener {

            findNavController().navigate(R.id.action_HomeFragment_to_ponyFragment)
        }

        botAdmin.setOnClickListener {

            findNavController().navigate(R.id.action_HomeFragment_to_adminFragment)
        }

        cvNosotros.setOnClickListener{

            findNavController().navigate(R.id.action_HomeFragment_to_teamFragment)
        }


    }
}