package com.okitoki.okchat.core

import android.app.Application
//import org.koin.android.ext.android.startKoin

class OKChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin(applicationContext, myDiModule)
    }
}