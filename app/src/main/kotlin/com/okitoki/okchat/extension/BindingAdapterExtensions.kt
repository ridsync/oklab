package com.okitoki.okchat.extension

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * @author ridsync
 */

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(visible: Boolean) {
    isRefreshing = visible
}

//@BindingAdapter("onSingleClick")
//fun View.onSingleClick(listener: (view: View) -> Unit) {
//    this.setOnClickListener { v ->
//        v.isClickable = false
//        postDelayed({ v.isClickable = true }, 500L)
//        listener.invoke(v)
//    }
//}
