package com.example.epi_app.paraDespues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epi_app.R
import kotlinx.android.synthetic.main.gallery_list.view.*

class GalleryAdapter :RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){

    private var galleryList= emptyList<String>()

    fun updateAdapter(mList: List<String>){
        galleryList=mList
        notifyDataSetChanged()
    }

    inner class GalleryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemImage=itemView.imgGallery


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.GalleryViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.gallery_list,parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.GalleryViewHolder, position: Int) {
        //holder.itemImage


    }

    override fun getItemCount()= galleryList.size


}