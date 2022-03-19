package com.okitoki.okchat.ui.sign

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.exoplayer2.util.EventDispatcher
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.orhanobut.logger.Logger


/**
 * Created by okwon on 2020/11/11.
 */

class MySMSBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
            val extras = intent.extras
            val status: Status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val message = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                    Logger.d("[SMS] onReceive message = $message")
                    sendSmsAuthCode(message)
                }
                CommonStatusCodes.TIMEOUT -> {
                }
            }
        }
    }

    private fun sendSmsAuthCode(message: String?){
        if(message == null) return
        try {
            Logger.i("[SMS] SmsBroadcastReceiver message = $message")
            val code = Regex("[0-9]+").findAll(message)
                .map(MatchResult::value)
                .filter { it.length >= 4 }
                .first()
            if (code.isNotEmpty()) {
                Logger.i("[SMS] SmsBroadcastReceiver code = $code")
//                EventBus.getDefault().post(EBAuthData(code))
            }
        }catch (e: Exception){
//            FirebaseCrashlytics.getInstance().recordException(e)
            e.printStackTrace()
        }
    }
}