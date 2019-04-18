package com.okitoki.okchat.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivitySplashBinding
import com.okitoki.okchat.ui.binding.BindingActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * Created by okc on 2019-04-01.
 */
class SplashActivity : BindingActivity<ActivitySplashBinding>() {

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = getViewModel()
        binding.setLifecycleOwner(this)


    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }


}
