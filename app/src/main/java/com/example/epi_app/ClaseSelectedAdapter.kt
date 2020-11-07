package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.viewmodel.EpiViewModel

import kotlinx.android.synthetic.main.claseselected_list.view.*

class ClaseSelectedAdapter:RecyclerView.Adapter<ClaseSelectedAdapter.ClaseSelViewHolder>() {

    private var claseSelectedList= emptyList<ClaseEntity>()


    fun UpdateAdapter(list:List<ClaseEntity>){
        claseSelectedList=list
        notifyDataSetChanged()

    }

    inner class ClaseSelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var fechaClaseReser = itemView.fechaYhoraReservada
        var horaReser = itemView.horaReservada
        var ponyReser=itemView.ponyReservado

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClaseSelectedAdapter.ClaseSelViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.claseselected_list,
            parent, false)

        return ClaseSelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClaseSelectedAdapter.ClaseSelViewHolder, position: Int) {
        val selectedClass=claseSelectedList[position]

        holder.fechaClaseReser.text=selectedClass.dia
        holder.horaReser.text=selectedClass.hora
        holder.ponyReser.text=selectedClass.id.toString() // cambiarlo por los cupos

    }

    override fun getItemCount()= claseSelectedList.size





}