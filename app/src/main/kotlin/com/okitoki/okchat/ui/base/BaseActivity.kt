package com.okitoki.okchat.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author ridsync
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
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
}