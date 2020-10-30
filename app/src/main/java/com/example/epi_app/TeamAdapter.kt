package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epi_app.model.local.Team
import kotlinx.android.synthetic.main.team_list.view.*

class TeamAdapter: RecyclerView.Adapter<TeamAdapter.TeamViewHolder>(){

    private var teamList= emptyList<Team>()
    fun updateAdapter(myList:List<Team>){
        teamList=myList
        notifyDataSetChanged()
    }

    inner class TeamViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val itemNombre=itemView.tvTeamNombre
        val itemCargo=itemView.tvTeamCargo
        val itemDesc=itemView.tvTeamText
        val itemPhoto=itemView.ivTeamPhoto
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.team_list, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {

        val foto=teamList[position].imagen

        holder.itemNombre.text=teamList[position].nombre
        holder.itemCargo.text=teamList[position].cargo
        holder.itemDesc.text=teamList[position].descripcion

        Glide.with(holder.itemView.context).load(foto).fitCenter().into(holder.itemPhoto)
    }

    override fun getItemCount()= teamList.size
}

