package com.okitoki.okchat.extension

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by okdev on 2019-12-04.
 */

fun View.setOnSingleClickListener(listener: (view: View) -> Unit) {
    this.setOnClickListener { v ->
        v.isClickable = false
        postDelayed({ v.isClickable = true }, 500L)
        listener.invoke(v)
    }
}

fun showToast(context: Context, message:String) {
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}



