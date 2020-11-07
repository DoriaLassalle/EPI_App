package com.example.epi_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epi_app.model.local.Tienda
import com.example.epi_app.viewmodel.EpiViewModel
import kotlinx.android.synthetic.main.fragment_team.*
import kotlinx.android.synthetic.main.fragment_tienda.*


class TiendaFragment : Fragment(), TiendaAdapter.ToCLick {

    private val myViewModel:EpiViewModel by activityViewModels()

    lateinit var myAdapter3:TiendaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myAdapter3= TiendaAdapter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tienda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        botTiendaHome.setOnClickListener {

            findNavController().navigate(R.id.action_tiendaFragment_to_HomeFragment)
        }

        val recyclerViewTienda=recyclerTienda
        recyclerViewTienda.layoutManager= GridLayoutManager(context, 2)
        recyclerViewTienda.adapter=myAdapter3
        myAdapter3.updateAdapter(myViewModel.getDataTienda())
    }

    override fun showMsg(tienda: Tienda) {
          Toast.makeText(context, "VENTA NO HABILITADA POR EL MOMENTO", Toast.LENGTH_SHORT).show()
    }
}