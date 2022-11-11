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
import com.android.sivano.domin.model.ProductsHomePage
import com.android.sivano.entities.Products
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import javax.inject.Inject

class ProductRecyclerView @Inject constructor() :
    RecyclerView.Adapter<ProductRecyclerView.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var discount = itemView.findViewById<TextView>(R.id.discount)
        var imageProduct = itemView.findViewById<ImageView>(R.id.product_Image)
        var oldPrice = itemView.findViewById<TextView>(R.id.old_price)
        var price = itemView.findViewById<TextView>(R.id.price)
        var title = itemView.findViewById<TextView>(R.id.product_Title)
        var btnSeeDetails = itemView.findViewById<MaterialButton>(R.id.see_details_btn)
        var btnAddToCart = itemView.findViewById<MaterialButton>(R.id.add_cart_btn)
        var imageheart = itemView.findViewById<ImageView>(R.id.heartImg)
        var imagediscount=itemView.findViewById<ImageView>(R.id.image_discount)
    }

    var productList: List<ProductsHomePage>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<ProductsHomePage>() {
        override fun areItemsTheSame(oldItem: ProductsHomePage, newItem: ProductsHomePage): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ProductsHomePage, newItem: ProductsHomePage): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        val dis:Int =product.discount
//        if (product.in_favorites)
//            holder.imageheart.setImageResource(R.drawable.redheart)
        holder.imageheart.setOnClickListener(View.OnClickListener {
            if(product.in_favorites)
                holder.imageheart.setImageResource(R.drawable.heartproduct)
            if(!product.in_favorites)
                holder.imageheart.setImageResource(R.drawable.redheart)

            onImageHeartClickListener?.let { it(product) }
        })
        holder.itemView.apply {

            "${product.price}LE".also { holder.price.text = it }
            Glide.with(context).load(product.image).into(holder.imageProduct)
            product.name.also { holder.title.text = it }

        }
        holder.btnSeeDetails.setOnClickListener(View.OnClickListener {
            onButtonItemClickListener?.let { it(product) }
        })
        holder.btnAddToCart.setOnClickListener(View.OnClickListener {
            onAddToCartClickListener?.let { it(product) }
        })
        if(dis==0) {
            holder.discount.visibility = View.GONE
            holder.oldPrice.visibility = View.GONE
            holder.imagediscount.visibility = View.GONE
        }else{
                "${product.discount}%".also { holder.discount.text = it }
                "${product.old_price}LE".also { holder.oldPrice.text = it }
                holder.oldPrice.paintFlags =
                    holder.oldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    override fun getItemCount() = productList.size

    private var onItemClickListener: ((ProductsHomePage) -> Unit)? = null
    private var onButtonItemClickListener: ((ProductsHomePage) -> Unit)? = null
    private var onImageHeartClickListener: ((ProductsHomePage) -> Unit)? = null
    private var onAddToCartClickListener: ((ProductsHomePage) -> Unit)? = null
    fun setOnItemClickListener(listener: (ProductsHomePage) -> Unit) {
        onItemClickListener = listener
    }
    fun setAddToCartClickListener(listener: (ProductsHomePage) -> Unit) {
        onAddToCartClickListener = listener
    }
    fun setOnButtonSeeDetailsClickListener(listener: (ProductsHomePage) -> Unit) {
        onButtonItemClickListener = listener
    }

    fun setOnImageHeartClickListener(listener: (ProductsHomePage) -> Unit) {
        onImageHeartClickListener = listener
    }
    fun setOnDeleteClickListener(listener: (ProductsHomePage) -> Unit) {
        onImageHeartClickListener = listener
    }
}