package com.droidknights.app2020.binding

import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.droidknights.app2020.R
import com.droidknights.app2020.data.Speaker

@BindingAdapter(value = ["bindImgRes"])
fun ImageView.bindSetImage(resId: Int) = setImageResource(resId)

@BindingAdapter(value = ["isRefreshing"])
fun SwipeRefreshLayout.bindRefreshing(isRefreshing: Boolean) {
    this.isRefreshing = isRefreshing
}

@BindingAdapter(value = ["onRefresh"])
fun SwipeRefreshLayout.bindRefreshListener(onRefreshListener: SwipeRefreshLayout.OnRefreshListener) =
    setOnRefreshListener(onRefreshListener)

@BindingAdapter("webUrl")
fun WebView.bindUrl(value: String?) = value?.let(::loadUrl)

@BindingAdapter("speakerName")
fun TextView.bindSpeakerName(value: Speaker?) {
    text = value?.name
}

@BindingAdapter("speakerImage")
fun ImageView.bindProfile(speaker: Speaker?) {
    Glide.with(this)
        .load(speaker?.profileImage)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.img_droid_space)
                .circleCrop()
        ).into(this)
}
