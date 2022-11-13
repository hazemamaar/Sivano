package com.android.sivano.ui.adabters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.sivano.R
import com.android.sivano.domin.model.CartItem
import com.android.sivano.entities.GetCartsDto
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import javax.inject.Inject

class CartsRecyclerView @Inject constructor() : RecyclerView.Adapter<CartsRecyclerView.CartsViewHolder>() {

    inner class CartsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imageProduct =itemView.findViewById<RoundedImageView>(R.id.product_Image)
        var imageDelete=itemView.findViewById<ImageView>(R.id.removeImg)
        var nameProduct =itemView.findViewById<TextView>(R.id.product_Title)
        var priceProduct=itemView.findViewById<TextView>(R.id.price)
        var minusTotal=itemView.findViewById<ImageView>(R.id.min_tot_cart)
        var plusTotal=itemView.findViewById<ImageView>(R.id.add)
        var mount=itemView.findViewById<TextView>(R.id.item_cart_mount)
    }

    var cartList: List<CartItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
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

    private var onItemClickListener: ((CartItem) -> Unit)? = null

    override fun onBindViewHolder(holder: CartsViewHolder, position: Int) {
        val cart = cartList[position]
        holder.itemView.apply {
            Glide.with(context).load(cart.product.image).into(holder.imageProduct)
            holder.nameProduct.text=cart.product.name
            holder.mount.text=cart.quantity.toString()
            holder.priceProduct.text=cart.product.price.toString()

        }
    }

    override fun getItemCount() = cartList.size

    fun setOnItemClickListener(listener: (CartItem) -> Unit) {
        onItemClickListener = listener
    }
}