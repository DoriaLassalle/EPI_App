package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epi_app.model.local.AlumnoEntity
import kotlinx.android.synthetic.main.admin_list.view.*


class AdminAdapter : RecyclerView.Adapter<AdminAdapter.AdminViewHolder>(){

    private var alumnoList= emptyList<AlumnoEntity>()

    fun UpdateAdapter(list:List<AlumnoEntity>){
        alumnoList=list;
        notifyDataSetChanged()

    }

    inner class AdminViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var nombAlum = itemView.adminNombre  //id del textview a mostrar en el recycler
        //var apellAlum = itemView.adminApellido
        var edadAlum = itemView.adminEdad
        var correoAlum = itemView.adminCorreo
        var apoderAlum = itemView.adminApoderado
        var expeAlum = itemView.adminExperiencia

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminAdapter.AdminViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.admin_list,
            parent, false)

        return AdminViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdminAdapter.AdminViewHolder, position: Int) {
        val newAlum=alumnoList[position]

        holder.nombAlum.text=("NOMBRE: ${newAlum.name} ${newAlum.lastName}")
        holder.edadAlum.text=("EDAD: ${newAlum.age.toString()}")
        holder.correoAlum.text=("CORREO: ${newAlum.email}")
        holder.apoderAlum.text=("APODERADO: ${newAlum.apoderado}")
        if (newAlum.experience==true){
            holder.expeAlum.text=("Con Experiencia")
        }else{
            holder.expeAlum.text=("Sin Experiencia")
        }




    }

    override fun getItemCount()= alumnoList.size

}