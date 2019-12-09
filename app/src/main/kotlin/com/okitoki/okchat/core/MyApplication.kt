package com.okitoki.okchat.core

import android.app.Application
import com.okitoki.okchat.di.*
import org.koin.android.ext.android.startKoin

class OKChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            networkModule,
            roomModule,
            apiModule,
            executorModule,
            repoModule,
            viewModelModule
        ))
    }
}