package com.android.sivano.ui.adabters;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.sivano.R
import com.android.sivano.model.Banner
import com.bumptech.glide.Glide

class BannersAdapter(var bannerList: List<Banner>) :
    RecyclerView.Adapter<BannersAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imgBanner = itemView.findViewById<ImageView>(R.id.image_banner)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_banner_layout,parent, false))
    }
    override fun getItemCount(): Int {
        return bannerList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val banner = bannerList[position]
            holder.itemView.apply {
                    Glide.with(context).load(banner.image).into(holder.imgBanner)
            }
    }
}