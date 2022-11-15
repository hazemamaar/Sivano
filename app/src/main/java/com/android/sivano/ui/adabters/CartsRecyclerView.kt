package com.android.sivano.ui.adabters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.sivano.R
import com.android.sivano.databinding.ItemCatLayoutBinding
import com.android.sivano.domin.model.CartItem
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.facebook.shimmer.ShimmerFrameLayout
import com.makeramen.roundedimageview.RoundedImageView
import javax.inject.Inject

class CartsRecyclerView @Inject constructor(
    private val context: Context,

) : RecyclerView.Adapter<CartsRecyclerView.CartsViewHolder>() {

    inner class CartsViewHolder(private val binding: ItemCatLayoutBinding) : RecyclerView.ViewHolder(binding.root){
      fun bind(item: CartItem){
          Glide.with(context).load(item.product.image).listener(object : RequestListener<Drawable?>{
              override fun onLoadFailed(
                  e: GlideException?,
                  model: Any?,
                  target: com.bumptech.glide.request.target.Target<Drawable?>?,
                  isFirstResource: Boolean
              ): Boolean {
                  binding.progressShimmer.stopShimmer()
                  binding.progressShimmer.visibility=View.GONE
                  return false
              }

              override fun onResourceReady(
                  resource: Drawable?,
                  model: Any?,
                  target: com.bumptech.glide.request.target.Target<Drawable?>?,
                  dataSource: com.bumptech.glide.load.DataSource?,
                  isFirstResource: Boolean
              ): Boolean {
                  binding.progressShimmer.stopShimmer()
                  binding.progressShimmer.visibility=View.GONE

                  return false
              }



          }).into(binding.productImage)
          binding.productTitle.text=item.product.name
          binding.itemCartMount.text=item.quantity.toString()

              binding.price.text=item.product.price.toString()
      }
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
        val itemBinding = ItemCatLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CartsViewHolder(
           itemBinding
        )
    }
    private var onItemClickListener: ((CartItem) -> Unit)? = null

    override fun onBindViewHolder(holder: CartsViewHolder, position: Int) {
        val cart = cartList[position]
        holder.apply {
            bind(cart)
        }
    }

    override fun getItemCount() = cartList.size

    fun setOnItemClickListener(listener: (CartItem) -> Unit) {
        onItemClickListener = listener
    }
}