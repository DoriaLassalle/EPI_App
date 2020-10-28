package com.example.epi_app.paraDespues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epi_app.R
import kotlinx.android.synthetic.main.fragment_galery.*
import kotlinx.android.synthetic.main.fragment_home.*


class GaleryFragment : Fragment() {


    lateinit var mAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_galery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView=recyclerGallery
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=mAdapter
        mAdapter.updateAdapter(getData())


        cvGallery.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_galeryFragment)
        }
    }

    fun getData():List<String>{
        val list= listOf<String>("a1", "clase0","clase1")
        return list
    }
}