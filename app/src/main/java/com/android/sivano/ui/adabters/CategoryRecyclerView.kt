package com.android.sivano.ui.adabters
import android.graphics.Color
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.sivano.R
import com.android.sivano.domin.model.CategoryItemModel
import com.google.android.material.button.MaterialButton
import javax.inject.Inject

class CategoryRecyclerView @Inject constructor() : RecyclerView.Adapter<CategoryRecyclerView.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var btnCategory=itemView.findViewById<MaterialButton>(R.id.category_btn)
    }
    var categoryList: List<CategoryItemModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<CategoryItemModel>() {
        override fun areItemsTheSame(oldItem: CategoryItemModel, newItem: CategoryItemModel): Boolean {
            return oldItem.hashCode()== newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CategoryItemModel, newItem: CategoryItemModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_category_layout,
                parent,
                false
            )
        )
    }
    private var onItemClickListener: ((CategoryItemModel) -> Unit)? = null

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.itemView.apply {
            Log.i("lalalal", "onBindViewHolder: "+"dn")
           holder.btnCategory.text=category.name
            holder.btnCategory.setOnClickListener(View.OnClickListener {
            })
            holder.btnCategory.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_MOVE ->{ //Do Something
                            holder.btnCategory.background.setTint(ContextCompat.getColor(context, R.color.black_app))
                             holder.btnCategory.setTextColor(Color.parseColor("#FFFFFFFF"))}
                    }
                    return v?.onTouchEvent(event) ?: true
                }
            })
//            holder.btnCategory.setOnDragListener(object : View.OnDragListener{
//                override fun onDrag(v: View?, event: DragEvent?): Boolean {
//                    when (event?.action) {
//                        MotionEvent.ACTION_DOWN ->{ //Do Something
//                            holder.btnCategory.background.setTint(ContextCompat.getColor(context, R.color.black_app))
//                            holder.btnCategory.setTextColor(Color.parseColor("#FFFFFFFF"))}
//                    }
//
//                    return v?.onDragEvent(event) ?: true
//                }
//            })
        }
    }
    override fun getItemCount() = categoryList.size

    fun setOnItemClickListener(listener: (CategoryItemModel) -> Unit) {
        onItemClickListener = listener
    }
}