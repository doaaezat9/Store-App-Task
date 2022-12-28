package task.doaaezzat.storeapp.ui.main

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import task.doaaezzat.storeapp.R

// to set image string to image view by using picasso
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imageUri = it.toUri().buildUpon().scheme("https").build()
        Picasso.with(imageView.context)
            .load(imageUri)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }
}

// set egp text format
@BindingAdapter("EGPUnitText")
fun bindTextViewToEGPUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.egp_unit_format), number)
}

// set rate text format
@BindingAdapter("RateUnitText")
fun bindTextViewToRate(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.rate_unit_format), number)
}