package com.okitoki.okchat.ui

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.fonts.Font
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.okitoki.okchat.R
import com.okitoki.okchat.ui.NotificationBroadCastReceiver.Companion.VOICECALL_NOTIFICATION_ID

/**
 * Created by okwon on 2022/02/09.
 * Description :
 */
class FullScreenIntentTestActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createNotiChannelHigh(this)
//        startNotificationFullIntent(this)


        fullscreenNotiWithAlarm(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun fullscreenNotiWithAlarm(context: Context){
        val DONIT_ALARM_LENGTH = 1 * 10_000

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationBroadCastReceiver::class.java).apply {
            action = "test"
        }
        val alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + DONIT_ALARM_LENGTH,
            alarmIntent)
    }

    private fun createNotiChannelHigh(context: Context) {
        // basic notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "채널이"
            val descriptionText = "알림 채널 설명"
            // 중요 부분. importance high로 설정해야 한다!
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                "channel_id",
                name,
                importance
            ).apply {
                description = descriptionText
            }
            channel.vibrationPattern = longArrayOf(0,3000)
            channel.enableVibration(false)
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
//            notificationManager.deleteNotificationChannel("channel_id_1");
        }
    }

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
            setContentTitle("fullscreen intent notification")
            setContentText("fullscreen intent notification!")
            setAutoCancel(true)
            setSound(RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE))
            setVibrate(longArrayOf(0,0))
            setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE)
            setCategory(NotificationCompat.CATEGORY_CALL)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setLocalOnly(true)
            priority = NotificationCompat.PRIORITY_MAX
            setContentIntent(fullscreenPendingIntent)
            // <-- set full screen intent
            setFullScreenIntent(fullscreenPendingIntent, true)
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(VOICECALL_NOTIFICATION_ID,builder.build())
    }

    @SuppressLint("ServiceCast")
    private fun turnScreenOnAndKeyguardOff(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            window.addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED    // deprecated api 27
                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD     // deprecated api 26
                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON   // deprecated api 27
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)
        }
        val keyguardMgr = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            keyguardMgr.requestDismissKeyguard(this, null)
        }
    }
}