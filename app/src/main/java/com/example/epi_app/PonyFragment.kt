package com.example.epi_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_pony.*


class PonyFragment : Fragment() {

    private val myViewModel: EpiViewModel by activityViewModels()

    lateinit var  myAdapter: PonyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAdapter= PonyAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pony, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myRecycler = recyclerPony
        myRecycler.layoutManager = LinearLayoutManager(context)
        myRecycler.adapter = myAdapter

        myViewModel.getData().observe(viewLifecycleOwner, Observer {
            myAdapter.updateAdapter(it)
            Log.d("lista", it.toString())
        })



        botPonyHome.setOnClickListener {
            findNavController().navigate(R.id.action_ponyFragment_to_HomeFragment)
        }
    }
}