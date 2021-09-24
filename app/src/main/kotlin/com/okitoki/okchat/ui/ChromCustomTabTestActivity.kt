package com.okitoki.okchat.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.browser.customtabs.CustomTabsIntent
import com.okitoki.okchat.R
import com.okitoki.okchat.databinding.ActivityFontStyleBinding
import com.okitoki.okchat.ui.base.BaseActivity
import com.orhanobut.logger.Logger
import android.os.Build
import android.content.Intent

import android.content.pm.PackageManager
import android.content.ActivityNotFoundException
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.core.content.ContextCompat
import com.okitoki.okchat.databinding.ActivityChromeCustomtabBinding
import androidx.browser.customtabs.CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION

import android.content.pm.ResolveInfo
import androidx.browser.customtabs.CustomTabsService


/**
 * Created by okc on 2019-04-01.
 */
class ChromCustomTabTestActivity : BaseActivity<ActivityChromeCustomtabBinding>() {

//    private val authViewModel: AuthViewModel by viewModel()

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_chrome_customtab

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.vm = getViewModel()
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {
        Logger.d("", "")
    }

    fun onClickStart(view: View) {
        val url = "https://www.naver.com/"
        val size = getCustomTabsPackages(applicationContext).size
        val resultStart = if(size > 0){
            startCustomTab(url)
        } else {
            startNativeWebApp(url)
        }
        if(!resultStart)
            // startWebView()
        // TODO 외부 브라우져(CustomTab포함)오픈 실패시 브라우져 없음 알림 or 내부 WebView로 표현.
        Logger.v("getCustomTabsPackages size = $size packages = $")
    }

    private fun startCustomTab(url: String) : Boolean {
        val custonTabBuilder = CustomTabsIntent.Builder()
        val colorSchemeBuilder = CustomTabColorSchemeParams.Builder().apply {
            setToolbarColor(ContextCompat.getColor(applicationContext,R.color.colorPrimary))
        }
        custonTabBuilder.setDefaultColorSchemeParams(colorSchemeBuilder.build())
        val customTabsIntent = custonTabBuilder.build()
        return try {
            customTabsIntent.launchUrl(this, Uri.parse(url))
            true
        } catch (ex: ActivityNotFoundException) {
//            FirebaseCrashlytics.getInstance().recordException(e)
            ex.printStackTrace()
            false
        }
    }

    private fun startNativeWebApp(url: String) : Boolean {
        val nativeAppIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return try {
            startActivity(nativeAppIntent)
            true
        } catch (ex: ActivityNotFoundException) {
//            FirebaseCrashlytics.getInstance().recordException(e)
            ex.printStackTrace()
            false
        }
    }

//    fun launchUri(context: Context, uri: Uri) {
//        val launched: Boolean = if (Build.VERSION.SDK_INT >= 30) launchNativeApi30(
//            context,
//            uri
//        ) else launchNativeBeforeApi30(context, uri)
//        if (!launched) {
//            CustomTabsIntent.Builder()
//                .build()
//                .launchUrl(context, uri)
//        }
//    }
//
//    fun launchNativeApi30(context: Context, uri: Uri?): Boolean {
//        val nativeAppIntent = Intent(Intent.ACTION_VIEW, uri)
//            .addCategory(Intent.CATEGORY_BROWSABLE)
//            .addFlags(
//                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_REQUIRE_NON_BROWSER
//            )
//        return try {
//            context.startActivity(nativeAppIntent)
//            true
//        } catch (ex: ActivityNotFoundException) {
//            false
//        }
//    }

//    private fun launchNativeBeforeApi30(context: Context, uri: Uri): Boolean {
//        val pm = context.packageManager
//
//        // Get all Apps that resolve a generic url
//        val browserActivityIntent = Intent()
//            .setAction(Intent.ACTION_VIEW)
//            .addCategory(Intent.CATEGORY_BROWSABLE)
//            .setData(Uri.fromParts("http", "", null))
//        val genericResolvedList = extractPackageNames(
//            pm.queryIntentActivities(browserActivityIntent, 0)
//        )
//
//        // Get all apps that resolve the specific Url
//        val specializedActivityIntent = Intent(Intent.ACTION_VIEW, uri)
//            .addCategory(Intent.CATEGORY_BROWSABLE)
//        val resolvedSpecializedList: kotlin.collections.MutableSet<String> = extractPackageNames(
//            pm.queryIntentActivities(specializedActivityIntent, 0)
//        )
//
//        // Keep only the Urls that resolve the specific, but not the generic
//        // urls.
//        resolvedSpecializedList.removeAll(genericResolvedList)
//
//        // If the list is empty, no native app handlers were found.
//        if (resolvedSpecializedList.isEmpty()) {
//            return false
//        }
//
//        // We found native handlers. Launch the Intent.
//        specializedActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        context.startActivity(specializedActivityIntent)
//        return true
//    }

    /**
     * 커스텀탭 기능 가능한 브라우저 패키지 리스트 반환
     */
    fun getCustomTabsPackages(context: Context): ArrayList<ResolveInfo> {
        val pm = context.packageManager
        // Get default VIEW intent handler.
        val activityIntent = Intent()
            .setAction(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .setData(Uri.fromParts("http", "", null))

        // Get all apps that can handle VIEW intents.
        val resolvedActivityList = pm.queryIntentActivities(activityIntent, 0)
        val packagesSupportingCustomTabs: ArrayList<ResolveInfo> = ArrayList()
        for (info in resolvedActivityList) {
            val serviceIntent = Intent()
            serviceIntent.action = ACTION_CUSTOM_TABS_CONNECTION
            serviceIntent.setPackage(info.activityInfo.packageName)
            // Check if this package also resolves the Custom Tabs service.
            if (pm.resolveService(serviceIntent, 0) != null) {
                packagesSupportingCustomTabs.add(info)
            }
        }
        Logger.v("packagesSupportingCustomTabs = $packagesSupportingCustomTabs")
        return packagesSupportingCustomTabs
    }
}
