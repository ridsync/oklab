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
            val status: Status = extras[SmsRetriever.EXTRA_STATUS] as Status
            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val message = extras[SmsRetriever.EXTRA_SMS_MESSAGE] as String
                    Logger.d("[SMS] onReceive message = $message")
                    if(message != null){
                        var parseMessage = message.substringAfterLast("[")
                        var authCode = parseMessage.substringBefore("]")
                        //  SMS message
                    }
                }
                CommonStatusCodes.TIMEOUT -> {
                }
            }
        }
    }
}