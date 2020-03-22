package com.okitoki.okchat.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.okitoki.okchat.R
import com.okitoki.okchat.extension.showProgressDialog

/**
 * @author ridsync
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    private var mProgressDialog: ProgressDialog? = null

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun initAfterBinding()

    protected lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        snackbarObserving()
        initAfterBinding()
    }


    private fun snackbarObserving() {
//        viewModel.observeSnackbarMessage(this) {
//            Snackbar.make(findViewById(android.R.id.content), it, Snackbar.LENGTH_LONG).show()
//        }
//        viewModel.observeSnackbarMessageStr(this){
//            Snackbar.make(findViewById(android.R.id.content), it, Snackbar.LENGTH_LONG).show()
//        }
    }

//    open fun isNetworkConnected(): Boolean {
//        return NetworkUtils.isNetworkConnected(applicationContext)
//    }
//
//    open fun openActivityOnTokenExpire() {
//        startActivity(LoginActivity.newIntent(this))
//        finish()
//    }

    open fun hideLoading() {
        mProgressDialog?.let{
            if(it.isShowing)
                it.cancel()
        }
    }

    open fun showLoading() {
        hideLoading()
        mProgressDialog = showProgressDialog(this)
        mProgressDialog?.show()
    }


}