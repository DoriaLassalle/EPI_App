package com.example.epi_app.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.epi_app.R
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_item_epipony.*


class ItemPonyFragment : Fragment() {

    private val myViewModel: EpiViewModel by activityViewModels()

     var reciboEpipony:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            reciboEpipony = arguments?.getString("ponyinfo")
            Log.d("IMGSECONG", reciboEpipony.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_epipony, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.ponyFaceSelected.observe(viewLifecycleOwner, Observer {
            val fullPony = ivPonyIndiv
            tvPonyNombre.text=it.nomPony
            tvPonyText.text=it.desc


            Glide.with(this).load(it.fullimage).fitCenter().into(fullPony)


        })



        botBackPony.setOnClickListener {
            findNavController().navigate(R.id.action_itemPonyFragment_to_epiPonyFragment)
        }
    }
}