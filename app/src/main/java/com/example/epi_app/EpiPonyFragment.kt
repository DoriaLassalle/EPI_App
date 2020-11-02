package com.example.epi_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_epipony.*


class EpiPonyFragment : Fragment(),  EpiPonyAdapter.passPonyData{

    private val myViewModel: EpiViewModel by activityViewModels()

    lateinit var myAdapter: EpiPonyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAdapter= EpiPonyAdapter(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_epipony, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView=recyclerGallery
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=myAdapter
        myAdapter.updateAdapter(myViewModel.getDataGallery())


        botGalleryHome.setOnClickListener {
            findNavController().navigate(R.id.action_epiPonyFragment_to_HomeFragment)
        }
    }

    override fun passEpiPony(epiPony: Int){
        //myViewModel.classSelect(claseInfo)
        Log.d("pony selec", epiPony.toString())
        val myBundle = Bundle()
        myBundle.putInt("ponyinfo", epiPony)

        findNavController().navigate(R.id.action_EpiPonyFragment_to_itemPonyFragment, myBundle)

    }


}