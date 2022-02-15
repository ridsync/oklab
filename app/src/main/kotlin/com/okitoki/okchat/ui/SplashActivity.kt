package com.okitoki.okchat.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.annotation.LayoutRes
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.IntxerstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
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
        initAdmob()
    }

    override fun initAfterBinding() {
        binding.tvVersion.text  = String.format("V %s",getAppVersion(applicationContext))
        Logger.d("App Version = %s", binding.tvVersion.text.toString())

        binding.BtnTest.setOnClickListener {
            startChromeCutomTabActivity()
        }

        binding.BtnAdmob.setOnClickListener {
//            showADMob()
            startAdmobActivity()
        }
    }

    private fun initCheckServer(){
        binding.vm?.reqServerCheck()
//        Handler().postDelayed({ startIntentFullScreenActivity() }, 1000L)
    }

    private fun startMainActivity(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
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

    private fun startAwsAmplifynActivity(){
        val intent = Intent(applicationContext, AwsAmplifyActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startLocationTestActivity(){
        val intent = Intent(applicationContext, LocationTestActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startFontStyleTestActivity(){
        val intent = Intent(applicationContext, FontStyleTestActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startChromeCutomTabActivity(){
        val intent = Intent(applicationContext, ChromCustomTabTestActivity::class.java)
        startActivity(intent)
//        finish()
    }

    private fun startIntentFullScreenActivity(){
        val intent = Intent(applicationContext, FullScreenIntentTestActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * 다른 화면에서 광고 띄우기
     */
    private fun startAdmobActivity(){
        val intent = Intent(applicationContext, AdmobActivity::class.java)
        startActivity(intent)
        finish()
    }

    private var mInterstitialAd: InterstitialAd? = null
    private val TAG = "AdMobTest"

    private fun initAdmob() {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this)
        Log.d(TAG, "Activity Hash - $this")

        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
                binding.tvVersion.text = "Ad was loaded."
            }
        })

    }

    override fun onBackPressed() {
        showADMob()
        finish()
//        Handler(Looper.getMainLooper()).postDelayed( {
//
//        }, 1000)
//         android.os.Process.killProcess(android.os.Process.myPid())
    }

    private fun showADMob(){
        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
//            override fun onAdImpression() {
//                Log.d(TAG, "Ad was onAdImpression.")
//                super.onAdImpression()
//            }
//            override fun onAdClicked() {
//                Log.d(TAG, "Ad was onAdClicked.")
//                super.onAdClicked()
//            }
            override fun onAdDismissedFullScreenContent() {
                Log.d(TAG, "Ad was dismissed.")
//                (this@SplashActivity).onBackPressed()
//                initAdmob()
//                finish()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                Log.d(TAG, "Ad failed to show.")
//                (this@SplashActivity).onBackPressed()
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(TAG, "Ad showed fullscreen content.")
                mInterstitialAd = null
            }
        }

        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
//            super.onBackPressed()
            Log.d(TAG, "The interstitial ad wasn't ready yet.")
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        Log.d(TAG, "onUserLeaveHint")
    }
}
