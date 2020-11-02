package com.example.epi_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_item_epipony.*


class ItemPonyFragment : Fragment() {

     var imagenPony:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            imagenPony = arguments?.getInt("ponyinfo") ?:
            Log.d("IMGSECONG", imagenPony.toString())
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
        val imageView = ivPonyIndiv
        Glide.with(this).load(imagenPony).fitCenter().into(imageView)


        botBackPony.setOnClickListener {
            findNavController().navigate(R.id.action_itemPonyFragment_to_epiPonyFragment)
        }
    }
}