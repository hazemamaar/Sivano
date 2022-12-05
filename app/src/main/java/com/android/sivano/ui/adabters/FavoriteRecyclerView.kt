package com.android.sivano.ui.adabters
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.sivano.R
import com.android.sivano.data.entities.favorite.Data
import com.android.sivano.domin.model.FavoriteData
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import javax.inject.Inject

class FavoriteRecyclerView @Inject constructor(): RecyclerView.Adapter<FavoriteRecyclerView.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var discount = itemView.findViewById<TextView>(R.id.discount)
        var imageProduct = itemView.findViewById<ImageView>(R.id.product_Image)
        var oldPrice = itemView.findViewById<TextView>(R.id.old_price)
        var price = itemView.findViewById<TextView>(R.id.price)
        var title = itemView.findViewById<TextView>(R.id.product_Title)
        var btnSeeDetails = itemView.findViewById<MaterialButton>(R.id.see_details_btn)
        var btnAddToCart = itemView.findViewById<MaterialButton>(R.id.add_cart_btn)
        var imageheart = itemView.findViewById<ImageView>(R.id.heartImg)
    }
    var favoriteList: List<Data>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_favorite_layout,
                parent,
                false
            )
        )
    }

    private var onItemClickListener: ((Data) -> Unit)? = null

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = favoriteList[position]
        holder.itemView.apply {
            "${favorite.product.discount.toString()}%".also { holder.discount.text = it }
            "${favorite.product.old_price}LE".also { holder.oldPrice.text = it }
            "${favorite.product.price}LE".also { holder.price.text = it }
            Glide.with(context).load(favorite.product.image).into(holder.imageProduct)
            favorite.product.name.also { holder.title.text = it }
            holder.oldPrice.paintFlags =
                holder.oldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }
    override fun getItemCount() = favoriteList.size

    fun setOnItemClickListener(listener: (Data) -> Unit) {
        onItemClickListener = listener
    }
}