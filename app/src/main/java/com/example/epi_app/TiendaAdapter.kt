package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epi_app.model.local.Team
import com.example.epi_app.model.local.Tienda
import kotlinx.android.synthetic.main.team_list.view.*
import kotlinx.android.synthetic.main.tienda_list.view.*

class TiendaAdapter:  RecyclerView.Adapter<TiendaAdapter.TiendaViewHolder>(){

    private var tiendaList= emptyList<Tienda>()
    fun updateAdapter(myList:List<Tienda>){
        tiendaList=myList
        notifyDataSetChanged()
    }

    inner class TiendaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemNombreProd=itemView.tvNombreProducto
        val itemPrecioProd=itemView.tvPrecioProducto
        val itemFotoProd=itemView.ivProducto

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiendaViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.tienda_list, parent, false)
        return TiendaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TiendaViewHolder, position: Int) {

        val fotoProd=tiendaList[position].imagenProducto

        holder.itemNombreProd.text=tiendaList[position].nombreProducto
        holder.itemPrecioProd.text=("$ ${tiendaList[position].precioProducto.toString()} pesos.")


        Glide.with(holder.itemView.context).load(fotoProd).fitCenter().into(holder.itemFotoProd)
    }

    override fun getItemCount()= tiendaList.size
}