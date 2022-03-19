package com.okitoki.okchat.ui

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivitySplashBinding
import com.okitoki.okchat.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel


/**
 * Created by okc on 2019-04-01.
 */
class AdmobActivity : BaseActivity<ActivitySplashBinding>() {

//    private val authViewModel: AuthViewModel by viewModel()

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = getViewModel()
        binding.lifecycleOwner = this
        initADmob()
    }

    override fun initAfterBinding() {
        binding.tvVersion.text = "Admob Test"
//        binding.tvVersion.text  = String.format("V %s",getAppVersion(applicationContext))
//        Logger.d("App Version = %s", binding.tvVersion.text.toString())

        binding.BtnAdmob.setOnClickListener {
            showADmob()
        }
    }

    private var mInterstitialAd: InterstitialAd? = null
    private val TAG = "AdMobTest"

    private fun initADmob() {
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this)

        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
                showADmob()
            }
        })

    }

    override fun onBackPressed() {
        showADmob()
//        Handler(Looper.getMainLooper()).postDelayed( {
//            super.onBackPressed()
//        }, 100)
//         android.os.Process.killProcess(android.os.Process.myPid())
    }

    private fun showADmob(){
        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdImpression() {
                Log.d(TAG, "Ad was onAdImpression.")
                super.onAdImpression()
            }
            override fun onAdClicked() {
                Log.d(TAG, "Ad was onAdClicked.")
                super.onAdClicked()
            }
            override fun onAdDismissedFullScreenContent() {
                Log.d(TAG, "Ad was dismissed.")
                (this@AdmobActivity).onBackPressed()
                initADmob()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                Log.d(TAG, "Ad failed to show.")
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(TAG, "Ad showed fullscreen content.")
                mInterstitialAd = null
            }
        }

        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            super.onBackPressed()
            Log.d(TAG, "The interstitial ad wasn't ready yet.")
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        Log.d(TAG, "onUserLeaveHint")
    }
}
