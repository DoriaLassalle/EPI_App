package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epi_app.model.local.ClaseEntity
import kotlinx.android.synthetic.main.clasedisponible_list.view.*

class ReservarClaseAdapter(val callback: PassData) : RecyclerView.Adapter<ReservarClaseAdapter.ClaseViewHolder>(){


    private var claseList= emptyList<ClaseEntity>()

    fun UpdateAdapter(list:List<ClaseEntity>){
        claseList=list;
        notifyDataSetChanged()

    }

    inner class ClaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var detalleClase = itemView.fechaYhoraDispo  //id del textview a mostrar en el recycler
        var teacher = itemView.profeDispo
        var categoria = itemView.nivelDispo
        var cupos = itemView.ponyDispo
        val click=itemView.setOnClickListener {
            callback.passClaseInfo(claseList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservarClaseAdapter.ClaseViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.clasedisponible_list,
            parent, false)

        return ClaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservarClaseAdapter.ClaseViewHolder, position: Int) {
        val newClass=claseList[position]

        holder.detalleClase.text=("CLASE PARA EL DÍA: ${newClass.dia} a las ${newClass.hora} horas.")
        holder.teacher.text=("CON EL PROFESOR: ${newClass.profesor}")
        holder.categoria.text=("NIVEL: ${newClass.nivel}")
        holder.cupos.text=("CUPOS DISPONIBLES: ${newClass.cupos}")

    }

    override fun getItemCount()= claseList.size


    interface PassData {
        fun passClaseInfo(claseInfo: ClaseEntity){

        }

    }
}