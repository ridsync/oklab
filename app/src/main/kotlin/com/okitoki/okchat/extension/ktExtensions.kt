package com.okitoki.okchat.extension

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.okitoki.okchat.R
import com.okitoki.okchat.ui.base.BaseActivity

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

/**
 *  TODO ProgressDialog Deprecated
 */
fun showProgressDialog(context: Context?) : ProgressDialog {
    val progressDialog = ProgressDialog(context)
    progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    progressDialog.setContentView(R.layout.progress_dialog)
    progressDialog.isIndeterminate = true
    progressDialog.setCancelable(false)
    progressDialog.setCanceledOnTouchOutside(false)
    return progressDialog
}



