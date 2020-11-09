package com.example.epi_app.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epi_app.R
import com.example.epi_app.model.local.ClaseEntity
import kotlinx.android.synthetic.main.clasedisponible_list.view.*

class ReservarClaseAdapter(val callback: PassData) : RecyclerView.Adapter<ReservarClaseAdapter.ClaseViewHolder>(){


    private var claseList= emptyList<ClaseEntity>()

    fun UpdateAdapter(list:List<ClaseEntity>){
        claseList=list
        notifyDataSetChanged()

    }

    inner class ClaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var numeroClase= itemView.idClaseDispo
        var detalleClase = itemView.fechaYhoraDispo  //id del textview a mostrar en el recycler
        var teacher = itemView.profeDispo
        var categoria = itemView.nivelDispo
        var cupos = itemView.ponyDispo
        val click=itemView.setOnClickListener {
            callback.passClaseInfo(claseList[adapterPosition])
            itemView.cvClaseDisponible.setCardBackgroundColor(Color.parseColor("#f4f3f3"))

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClaseViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(
            R.layout.clasedisponible_list,
            parent, false)

        return ClaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClaseViewHolder, position: Int) {
        val newClass=claseList[position]

        holder.numeroClase.text=("CLASE N° ${newClass.id} ")
        holder.detalleClase.text=("Fecha: ${newClass.dia} - Hora: ${newClass.hora}")
        holder.teacher.text=("Profesor: ${newClass.profesor}")
        holder.categoria.text=("Nivel: ${newClass.nivel}")
        holder.cupos.text=("Cupos Disponibles: ${newClass.cupos}")


    }

    override fun getItemCount()= claseList.size


    interface PassData {
        fun passClaseInfo(claseInfo: ClaseEntity){   //aqui recibo el item que seleccionó

        }

    }
}