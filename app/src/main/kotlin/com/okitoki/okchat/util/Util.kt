package com.okitoki.okchat.util

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.orhanobut.logger.Logger


/**
 * Created by okc on 2020-03-22.
 */

fun getAppVersion(context: Context): String? { // application version
    var versionName = ""
    try {
        val info: PackageInfo =
            context.packageManager.getPackageInfo(context.packageName, 0)
        versionName = info.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        Logger.d("version not found error : %s",e.printStackTrace())
    }
    return versionName
}
