package com.okitoki.okchat.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.annotation.LayoutRes
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivitySplashBinding
import com.okitoki.okchat.ui.base.BaseActivity
import com.okitoki.okchat.ui.sign.JoinActivity
import com.okitoki.okchat.ui.sign.LoginActivity
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

        Handler().postDelayed({ startLoginActivity() }, 2000L)

    }

    fun startLoginActivity(){
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun startJoinActivity(){
        val intent = Intent(applicationContext, JoinActivity::class.java)
        startActivity(intent)
        finish()
    }

}
