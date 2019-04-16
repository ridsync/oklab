package com.okitoki.okchat.extension

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * @author ridsync
 */

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(visible: Boolean) {
    isRefreshing = visible
}