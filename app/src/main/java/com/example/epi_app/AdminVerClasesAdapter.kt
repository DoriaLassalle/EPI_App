package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epi_app.model.local.ClaseEntity
import com.example.epi_app.model.local.ClaseWithAlumnos
import kotlinx.android.synthetic.main.adminverclases_list.view.*
import kotlinx.android.synthetic.main.clasedisponible_list.view.*

class AdminVerClasesAdapter(val callbackAdmin: AdminSelClass ):RecyclerView.Adapter<AdminVerClasesAdapter.VerClasesViewHolder>() {

    private var clasesList= emptyList<ClaseEntity>()

    fun updteAdpater(list: List<ClaseEntity>){
        clasesList=list
        notifyDataSetChanged()
    }

    inner class VerClasesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var numClase= itemView.adminClaseDispo
        var detClase = itemView.adminFechaYhoraDispo
        var teacherClase = itemView.adminProfeDispo
        var catClase = itemView.adminNivelDispo
        var cuposClase = itemView.adminPonyDispo
        val click=itemView.setOnClickListener {
            callbackAdmin.showDialog(clasesList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerClasesViewHolder {
      val view=LayoutInflater.from(parent.context).inflate((R.layout.adminverclases_list), parent,
          false)
        return VerClasesViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerClasesViewHolder, position: Int) {
        val lesson=clasesList[position]

        holder.numClase.text= ("CLASE N° ${lesson.id} ")
        holder.detClase.text=("Fecha: ${lesson.dia} - Hora: ${lesson.hora}")
        holder.teacherClase.text=("Profesor: ${lesson.profesor}")
        holder.catClase.text=("Nivel: ${lesson.nivel}")
        holder.cuposClase.text=("Cupos Disponibles: ${lesson.cupos}")


    }

    override fun getItemCount()=clasesList.size

    interface AdminSelClass{
        fun showDialog(adminClase: ClaseEntity)
    }


}