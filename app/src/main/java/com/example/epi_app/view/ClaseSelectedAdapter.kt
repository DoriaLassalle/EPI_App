package com.example.epi_app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epi_app.R
import com.example.epi_app.model.local.ClaseEntity

import kotlinx.android.synthetic.main.claseselected_list.view.*

class ClaseSelectedAdapter:RecyclerView.Adapter<ClaseSelectedAdapter.ClaseSelViewHolder>() {

    private var claseSelectedList= emptyList<ClaseEntity>()


    fun UpdateAdapter(list: List<ClaseEntity>){
        claseSelectedList=list
        notifyDataSetChanged()

    }

    inner class ClaseSelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var fechaClaseReser = itemView.fechaYhoraReservada
        var horaReser = itemView.horaReservada
        var ponyReser=itemView.ponyReservado

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClaseSelViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(
            R.layout.claseselected_list,
            parent, false)

        return ClaseSelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClaseSelViewHolder, position: Int) {
        val selectedClass=claseSelectedList[position]

        holder.fechaClaseReser.text=selectedClass.dia
        holder.horaReser.text="${selectedClass.hora} horas"
       // holder.ponyReser.text="Clase N° ${selectedClass.id}

    }

    override fun getItemCount()= claseSelectedList.size





}