package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.epipony_list.view.*

class EpiPonyAdapter(val callback2: EpiPonyFragment) :RecyclerView.Adapter<EpiPonyAdapter.EpiPonyViewHolder>(){

    private var epiPonyList= emptyList<Int>()

    fun updateAdapter(mList: List<Int>){
        epiPonyList=mList
        notifyDataSetChanged()
    }

    inner class EpiPonyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemImage=itemView.imgGallery
        val click=itemView.setOnClickListener {
            callback2.passEpiPony(epiPonyList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpiPonyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.epipony_list,parent, false)
        return EpiPonyViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpiPonyViewHolder, position: Int) {
            val galFoto=epiPonyList[position]

        Glide.with(holder.itemView.context).load(galFoto).fitCenter().into(holder.itemImage)

    }

    override fun getItemCount()= epiPonyList.size

    interface passPonyData{
        fun passEpiPony(epiPony: Int)

    }


}