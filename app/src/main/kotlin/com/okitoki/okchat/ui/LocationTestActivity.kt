package com.okitoki.okchat.ui

import android.location.Geocoder
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivitySplashBinding
import com.okitoki.okchat.ui.base.BaseActivity
import com.okitoki.okchat.util.GeocodeUtil
import com.okitoki.okchat.util.getAppVersion
import com.orhanobut.logger.Logger
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*


/**
 * Created by okc on 2019-04-01.
 */
class LocationTestActivity : BaseActivity<ActivitySplashBinding>() {

//    private val authViewModel: AuthViewModel by viewModel()

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
        initGeoCoder()
    }

    override fun initAfterBinding() {
        binding.tvVersion.text  = "GeocodeUtil isPresent = ${Geocoder.isPresent()}"
        Logger.d("GeocodeUtil isPresent = %s", Geocoder.isPresent())
    }

    private fun initGeoCoder(){
        Logger.d("[GeocodeUtil] Geocoder.isPresent() = " + Geocoder.isPresent())

        // 서울특별시 강남구 신사동 550-11
        val location = GeocodeUtil.GeoLocation(37.523290, 127.024021)

        val geo = GeocodeUtil(this)
        val list: ArrayList<String> = geo.getAddressListUsingGeolocation(location)
        Logger.d("[GeocodeUtil] $list")

        // 서울특별시 강남구 역삼동 강남대로 364 미왕빌딩 6층
        val location2 = GeocodeUtil.GeoLocation(37.495537, 127.029347)

        val list2: ArrayList<String> = geo.getAddressListUsingGeolocation(location2)
        Logger.d("[GeocodeUtil] $list2")

        // 경기도 부천시 원미구 중1동
        val location3 = GeocodeUtil.GeoLocation(37.502363, 126.765189)

        val list3: ArrayList<String> = geo.getAddressListUsingGeolocation(location3)
        Logger.d("[GeocodeUtil] $list3")

        // 부전동 397-39번지 지하1층 부산진구 부산광역시 KR
        val location4 = GeocodeUtil.GeoLocation(35.159423, 129.057586)

        val list4: ArrayList<String> = geo.getAddressListUsingGeolocation(location4)
        Logger.d("[GeocodeUtil] $list4")
    }

}
