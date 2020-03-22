package com.okitoki.okchat.ui.sign

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivityJoinBinding
import com.okitoki.okchat.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel


/**
 * Created by okc on 2019-04-01.
 */
class JoinActivity : BaseActivity<ActivityJoinBinding>() {


    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_join

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {

    }
}
