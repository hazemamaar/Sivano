package com.android.sivano.ui.adabters;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.sivano.R
import com.bumptech.glide.Glide

class ViewPagerImageDetails(var imageList: List<String>
) : RecyclerView.Adapter<ViewPagerImageDetails.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var image =itemView.findViewById<ImageView>(R.id.product_Image_viewPager)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager_details,parent, false))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val image=imageList[position]
        holder.itemView.apply {
        Glide.with(context).load(image).into(holder.image)}
    }
}