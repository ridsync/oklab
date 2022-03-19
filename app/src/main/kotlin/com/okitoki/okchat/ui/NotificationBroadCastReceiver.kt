package com.okitoki.okchat.ui

import android.annotation.SuppressLint
import android.app.*
import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.okitoki.okchat.R

/**
 * Created by okwon on 2022/02/09.
 * Description :
 */
class NotificationBroadCastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("NotiBroadcastReceiver", "action : ${intent?.action}, extra : ${intent?.getIntExtra(EXTRA_NOTIFICATION_ID, 0)}")
        if (intent != null) {
            if (context != null) {
                startNotificationFullIntent(context)
            }
        }
    }
//    private fun createNotiChannelHigh(context: Context) {
//        // basic notification channel
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "channel_name"
//            val descriptionText = "channel_desc"
//            // 중요 부분. importance high로 설정해야 한다!
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val channel = NotificationChannel(
//                "channel_id",
//                name,
//                importance
//            ).apply {
//                description = descriptionText
//            }
//            val notificationManager =
//                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }

    private fun startNotificationFullIntent(context: Context){
//        val intent = Intent(context, FullScreenIntentTestActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val pendingIntent = PendingIntent.getActivity(context, 0,intent, 0)
// fullscreen 용 Activity Intent 생성
        val fullscreenIntent = Intent(context, FullScreenCallActivity::class.java).apply {
            action = "fullscreen_activity"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val fullscreenPendingIntent = PendingIntent.getActivity(context, 0, fullscreenIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context,"channel_id").apply {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentTitle("fullscreen Content Title")
            setContentText("fullscreen Content Text!")
            setAutoCancel(true)
            setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE)
            setCategory(NotificationCompat.CATEGORY_CALL)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setLocalOnly(true)
            priority = NotificationCompat.PRIORITY_HIGH
//            setContentIntent(fullscreenPendingIntent)
            // <-- set full screen intent
            setFullScreenIntent(fullscreenPendingIntent, true)
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(VOICECALL_NOTIFICATION_ID,builder.build())
    }

//    @SuppressLint("ServiceCast")
//    private fun turnScreenOnAndKeyguardOff(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
//            setShowWhenLocked(true)
//            setTurnScreenOn(true)
//            window.addFlags(
//                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                        or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)
//        } else {
//            window.addFlags(
//                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED    // deprecated api 27
//                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD     // deprecated api 26
//                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON   // deprecated api 27
//                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)
//        }
//        val keyguardMgr = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            keyguardMgr.requestDismissKeyguard(this, null)
//        }
//    }

    companion object {
        val VOICECALL_NOTIFICATION_ID = 1004
    }
}