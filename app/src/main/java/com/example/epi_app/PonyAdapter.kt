package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.network.Pony
import kotlinx.android.synthetic.main.pony_list.view.*

class PonyAdapter: RecyclerView.Adapter<PonyAdapter.PonyViewHolder>() {

    private var ponyList= emptyList<Pony>()



    inner class PonyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imgPony = itemView.imgPonyFace  //id del textview a mostrar en el recycler
        val namePony= itemView.tvPonyName


            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PonyAdapter.PonyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.pony_list,
            parent, false)

        return PonyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PonyAdapter.PonyViewHolder, position: Int) {
       val newPony=ponyList[position]

        //holder.imgPony.text= newPony.image   es una imagen no se que poner aqui
        holder.namePony.text=newPony.nameP
    }

    override fun getItemCount()= ponyList.size


}