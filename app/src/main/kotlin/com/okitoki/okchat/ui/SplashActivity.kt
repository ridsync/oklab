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
import com.okitoki.okchat.ui.viewmodel.AuthViewModel
import com.okitoki.okchat.util.getAppVersion
import com.orhanobut.logger.Logger
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by okc on 2019-04-01.
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

//    private val authViewModel: AuthViewModel by viewModel()

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
        initCheckServer()
    }

    override fun initAfterBinding() {
        binding.tvVersion.text  = String.format("V %s",getAppVersion(applicationContext))
        Logger.d("App Version = %s", binding.tvVersion.text.toString())
    }

    private fun initCheckServer(){
        binding.vm?.reqServerCheck()
        Handler().postDelayed({ startLoginActivity() }, 2000L)
    }

    private fun startLoginActivity(){
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startJoinActivity(){
        val intent = Intent(applicationContext, JoinActivity::class.java)
        startActivity(intent)
        finish()
    }

}
