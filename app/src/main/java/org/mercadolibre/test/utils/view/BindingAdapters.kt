package org.mercadolibre.test.utils.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import org.mercadolibre.test.utils.extensions.setImageFromUrl

class BindingAdapters {
    companion object {
        @BindingAdapter("android:image_url")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, auctionImageUrl: String) {
            imageView.setImageFromUrl(auctionImageUrl)
        }
    }
}