package com.okitoki.okchat.core

import android.app.Application
import com.okitoki.okchat.di.apiModule
import com.okitoki.okchat.di.networkModule
import com.okitoki.okchat.di.roomModule
import com.okitoki.okchat.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class OKChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@OKChatApplication)
            // load properties from assets/koin.properties file
            androidFileProperties()
            modules( listOf(networkModule,
                     apiModule,
                     roomModule,
                     viewModelModule))
        }
    }
}