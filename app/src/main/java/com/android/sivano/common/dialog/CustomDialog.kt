package com.android.sivano.common.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.*
import com.android.sivano.R
import com.android.sivano.databinding.DialogItemSeeDetailsBinding
import com.android.sivano.entities.homepage.CompleteProductDto
import com.android.sivano.ui.adabters.ViewPagerImageDetails


object CustomDialog {

    fun  showDialogDetails(
        context: Context,
        product: CompleteProductDto,

        ) {

        val dialogBuilder: AlertDialog.Builder =
            AlertDialog.Builder(context)
        val dialog: Dialog
        val bind: DialogItemSeeDetailsBinding =
            DialogItemSeeDetailsBinding.inflate(LayoutInflater.from(context))


        if (bind.root != null) {
            (bind.root as ViewGroup).removeView(bind.root)
        }
        dialogBuilder
            .setView(bind.root)
            .setCancelable(true)
        dialog = dialogBuilder.create()
        dialog.window?.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.show()
        bind.description.text=product.description
        (product.price.toString()+"LE").also{ bind.price.text = it }
//        (product.old_price.toString()+"LE").also { bind.oldPrice.text = it }
//        bind.oldPrice.paintFlags=
//            bind.oldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//        Glide.with(context).load(product.image).into(bind.productImage)
        bind.backIcon.setOnClickListener {
            dialog.dismiss()
        }
        bind.viewpager2Details.adapter=ViewPagerImageDetails(product.images)
        bind.productTitle.text=product.name
        val textRedMoreValue = bind.textReadMore.text.toString()
        bind.textReadMore.setOnClickListener(View.OnClickListener {
            if (textRedMoreValue == context.getString(R.string.read_more)) {
                bind.description.maxLines = Int.MAX_VALUE
                bind.description.ellipsize = null
                bind.textReadMore.text = context.getString(R.string.read_less)
            } else {
                bind.description.maxLines = 2
                bind.description.ellipsize = TextUtils.TruncateAt.END
                bind.textReadMore.text = context.getString(R.string.read_more)
            }

        })

//        ("Save "+product.discount+"%").also { bind.save.text=it }
//        glide.load(product.images[0]).into(bind.productImg)
//        bind.productDiscountTv.text="${product.discountPercentage}%"
//        bind.productPrice.text="LE ${product.price}"
//        bind.productRating.text="${product.rating}"
//        bind.textDescripition.text=product.description
//        bind.productNameTv.text=product.title
//        bind.textReadMore.setOnClickListener {
//            val textRedMoreValue = bind.textReadMore.text.toString()
//            if (textRedMoreValue == context.getString(AppStrings.read_more)) {
//                bind.textDescripition.maxLines = Int.MAX_VALUE
//                bind.textDescripition.ellipsize = null
//                bind.textReadMore.text = context.getString(AppStrings.read_less)
//            } else {
//                bind.textDescripition.maxLines = 2
//                bind.textDescripition.ellipsize = TextUtils.TruncateAt.END
//                bind.textReadMore.text = context.getString(AppStrings.read_more)
//            }
//        }
//        bind.backIcon.setOnClickListener {
//            // no button id = -1
//            dialog.dismiss()
//        }
    }


//
//    fun showDialogForLogout(
//        context: Context,
//        logoutClickListener: (Boolean) -> Unit,
//    ) {
//        val dialogBuilder: AlertDialog.Builder =
//            AlertDialog.Builder(context)
//        val dialog: Dialog
//        val bind: LogoutDialogLayoutBinding =
//            LogoutDialogLayoutBinding.inflate(LayoutInflater.from(context))
//
//        if (bind.root != null) {
//            (bind.root as ViewGroup).removeView(bind.root)
//        }
//        dialogBuilder
//            .setView(bind.root)
//            .setCancelable(true)
//        dialog = dialogBuilder.create()
//        dialog.window?.setBackgroundDrawable(
//            ColorDrawable(Color.TRANSPARENT)
//        )
//        dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
//
//        dialog.show()
//
//        bind.logoutBtnYes.setOnClickListener {
//            // no button id = -1
//            dialog.dismiss()
//            logoutClickListener(true)
//        }
//        bind.logoutCancelBtn.setOnClickListener {
//            // no button id = -1
//            logoutClickListener(false)
//            dialog.dismiss()
//        }
//    }


}