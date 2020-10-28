package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epi_app.model.local.AlumnoEntity
import com.example.epi_app.model.local.PonyEntity
import com.example.epi_app.model.network.Pony
import kotlinx.android.synthetic.main.pony_list.view.*

class PonyAdapter: RecyclerView.Adapter<PonyAdapter.PonyViewHolder>() {

    private var ponyList= emptyList<PonyEntity>()

    fun updateAdapter(myList: List<PonyEntity>){                 //actualizar el adapt
        ponyList=myList
        notifyDataSetChanged()
    }



    inner class PonyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imgPony = itemView.imgPonyFace  //id del textview a mostrar en el recycler
        val likesPony= itemView.tvPonyName


            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PonyAdapter.PonyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.pony_list,
            parent, false)

        return PonyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PonyAdapter.PonyViewHolder, position: Int) {
       val newPony=ponyList[position]


        //Glide que implementé en gradle para mostrar en el image view
        Glide.with(holder.itemView.context).load(newPony.largeImageURL).into(holder.imgPony)

        holder.likesPony.text=newPony.likes
    }

    override fun getItemCount()= ponyList.size


}