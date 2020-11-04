package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epi_app.model.local.EpiPony
import kotlinx.android.synthetic.main.epipony_list.view.*

class EpiPonyAdapter(val callback2: EpiPonyFragment) :RecyclerView.Adapter<EpiPonyAdapter.EpiPonyViewHolder>(){

    private var epiPonyList= emptyList<EpiPony>()

    fun updateAdapter(mList: List<EpiPony>){
        epiPonyList=mList
        notifyDataSetChanged()
    }

    inner class EpiPonyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemPonyImg=itemView.ivPonyFace
        val click=itemView.setOnClickListener {
            callback2.passEpiPony(epiPonyList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpiPonyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.epipony_list,parent, false)
        return EpiPonyViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpiPonyViewHolder, position: Int) {
            val faceFoto=epiPonyList[position].imageponyFace  // le paso el valor que tenga en la clase

        Glide.with(holder.itemView.context).load(faceFoto).fitCenter().into(holder.itemPonyImg)

    }

    override fun getItemCount()= epiPonyList.size

    interface passPonyData{
        fun passEpiPony(epiPony: EpiPony)

    }


}