package com.example.epi_app.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.epi_app.R
import com.example.epi_app.model.local.EpiPony
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_epipony.*


class EpiPonyFragment : Fragment(), EpiPonyAdapter.passPonyData {

    private val myViewModel: EpiViewModel by activityViewModels()

    lateinit var myAdapter4: EpiPonyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAdapter4= EpiPonyAdapter(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_epipony, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView=recyclerEpiPony
        recyclerView.layoutManager= GridLayoutManager(context, 2)
        recyclerView.adapter=myAdapter4
        myAdapter4.updateAdapter(myViewModel.getDataHorse())


        botGalleryHome.setOnClickListener {
            findNavController().navigate(R.id.action_epiPonyFragment_to_HomeFragment)
        }
    }

    override fun passEpiPony(epiPony: EpiPony){
        myViewModel.ponyFaceSelect(epiPony)
        Log.d("pony selec", epiPony.toString())
       // val myBundle = Bundle()
        //myBundle.putSerializable("ponyinfo", epiPony)

        findNavController().navigate(R.id.action_EpiPonyFragment_to_itemPonyFragment)

    }


}