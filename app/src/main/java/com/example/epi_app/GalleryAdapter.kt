package com.example.epi_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.gallery_list.view.*

class GalleryAdapter :RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){

    private var galleryList= emptyList<Int>()

    fun updateAdapter(mList: List<Int>){
        galleryList=mList
        notifyDataSetChanged()
    }

    inner class GalleryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemImage=itemView.imgGallery

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.gallery_list,parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
            val galFoto=galleryList[position]

        Glide.with(holder.itemView.context).load(galFoto).fitCenter().into(holder.itemImage)

    }

    override fun getItemCount()= galleryList.size


}