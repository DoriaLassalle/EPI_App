package com.example.epi_app.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epi_app.R
import com.example.epi_app.model.local.PonyEntity
import kotlinx.android.synthetic.main.pony_list.view.*

class PonyAdapter: RecyclerView.Adapter<PonyAdapter.PonyViewHolder>() {

    private var ponyList= emptyList<PonyEntity>()

    fun updateAdapter(myList: List<PonyEntity>){                 //actualizar el adapt
        ponyList=myList
        notifyDataSetChanged()
        Log.d("listado", ponyList.size.toString())
    }

    inner class PonyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imgPony = itemView.imgPonyFace  //id del textview a mostrar en el recycler
        val likesPony= itemView.tvPonyName


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PonyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.pony_list, parent, false)

        return PonyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PonyViewHolder, position: Int) {
       val newPony=ponyList[position]

        Glide.with(holder.itemView.context).load(newPony.largeImageURL).centerCrop().into(holder.imgPony)

        holder.likesPony.text="${newPony.likes} Likes "
    }

    override fun getItemCount()= ponyList.size



}