package com.example.epi_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.HORIZONTAL
import android.widget.LinearLayout.HORIZONTAL
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_galery.*


class GaleryFragment : Fragment() {

    private val myViewModel: EpiViewModel by activityViewModels()

    lateinit var myAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myAdapter= GalleryAdapter()

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
        recyclerView.adapter=myAdapter
        myAdapter.updateAdapter(myViewModel.getDataGallery())


        botGalleryHome.setOnClickListener {
            findNavController().navigate(R.id.action_galeryFragment_to_HomeFragment)
        }
    }


}