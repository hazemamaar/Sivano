package com.android.sivano.ui.adabters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.sivano.R
import com.android.sivano.entities.GetCartsDto

class CartsRecyclerView : RecyclerView.Adapter<CartsRecyclerView.CartsViewHolder>() {

    inner class CartsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var cartList: List<GetCartsDto>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<GetCartsDto>() {
        override fun areItemsTheSame(oldItem: GetCartsDto, newItem: GetCartsDto): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: GetCartsDto, newItem: GetCartsDto): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartsViewHolder {
        return CartsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cat_layout,
                parent,
                false
            )
        )
    }

    private var onItemClickListener: ((GetCartsDto) -> Unit)? = null

    override fun onBindViewHolder(holder: CartsViewHolder, position: Int) {
        val cart = cartList[position]
        holder.itemView.apply {


        }
    }

    override fun getItemCount() = cartList.size

    fun setOnItemClickListener(listener: (GetCartsDto) -> Unit) {
        onItemClickListener = listener
    }
}