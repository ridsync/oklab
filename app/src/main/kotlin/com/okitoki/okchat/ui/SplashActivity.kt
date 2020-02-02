package com.okitoki.okchat.ui

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivitySplashBinding
import com.okitoki.okchat.extension.setOnSingleClickListener
import com.okitoki.okchat.extension.showToast
import com.okitoki.okchat.ui.base.BaseActivity
import com.okitoki.okchat.util.NotNullMutableLiveData
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * Created by okc on 2019-04-01.
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {

        showLoading()

        Handler().postDelayed({ hideLoading() }, 8000L)

    }


}
