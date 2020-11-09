package com.example.epi_app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epi_app.R
import com.example.epi_app.model.local.AlumnoEntity

class AdminClassAlumAdapter : RecyclerView.Adapter<AdminClassAlumAdapter.AlumViewHolder>(){



    private var alumnosConClaseList= emptyList<AlumnoEntity>()

    fun updateAsapter(list: List<AlumnoEntity>){
        alumnosConClaseList=list
        notifyDataSetChanged()

    }

    inner class AlumViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(
            R.layout.adminclassalum_list,
            parent, false)

        return AlumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlumViewHolder, position: Int) {

    }

    override fun getItemCount()= alumnosConClaseList.size

}