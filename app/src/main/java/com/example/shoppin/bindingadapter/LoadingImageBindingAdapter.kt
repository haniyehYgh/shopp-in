package com.example.shoppin.bindingadapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.shoppin.R


class LoadImageBindingAdapter {


    companion object {


        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageUrl(imgView: ImageView, imgUrl: String?) {

            imgUrl?.let {
  /*              val imgUri = it.toUri().buildUpon().scheme("https").build()
                Glide.with(imgView.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_not_message)
                            .error(R.drawable.ic_not_message)
                    )
                    .into(imgView)*/
            }
        }


    }
}