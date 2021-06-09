package com.okitoki.okchat.ui

import android.location.Geocoder
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivityFontStyleBinding
import com.okitoki.okchat.databinding.ActivitySplashBinding
import com.okitoki.okchat.ui.base.BaseActivity
import com.okitoki.okchat.util.GeocodeUtil
import com.okitoki.okchat.util.getAppVersion
import com.orhanobut.logger.Logger
import java.util.*


/**
 * Created by okc on 2019-04-01.
 */
class FontStyleTestActivity : BaseActivity<ActivityFontStyleBinding>() {

//    private val authViewModel: AuthViewModel by viewModel()

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_font_style

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.vm = getViewModel()
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {
        Logger.d("", "")
    }

}
