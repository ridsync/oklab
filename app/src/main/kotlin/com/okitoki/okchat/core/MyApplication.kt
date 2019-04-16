package com.okitoki.okchat.core

import android.app.Application
import com.okitoki.okchat.di.apiModule
import com.okitoki.okchat.di.networkModule
import com.okitoki.okchat.di.roomModule
import com.okitoki.okchat.di.viewModelModule
import org.koin.android.ext.android.startKoin

class OKChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            networkModule,
            apiModule,
            roomModule,
            viewModelModule
        ))
    }
}