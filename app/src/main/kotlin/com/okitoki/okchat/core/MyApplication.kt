package com.okitoki.okchat.core

import android.app.Application
import com.okitoki.okchat.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OKChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@OKChatApplication)
            // load properties from assets/koin.properties file
            androidFileProperties()

            modules(listOf(
                networkModule,
                roomModule,
                apiModule,
                executorModule,
                repoModule,
                viewModelModule))
        }
    }
}