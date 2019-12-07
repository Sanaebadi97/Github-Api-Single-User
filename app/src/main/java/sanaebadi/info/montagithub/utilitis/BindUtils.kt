package sanaebadi.info.montagithub.utilitis

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import sanaebadi.info.montagithub.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions().optionalCircleCrop()
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.error_image)
            )
            .into(imgView)
    }
}