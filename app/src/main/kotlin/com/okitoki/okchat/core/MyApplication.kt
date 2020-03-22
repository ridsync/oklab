package com.okitoki.okchat.core

import android.app.Application
import com.okitoki.okchat.BuildConfig
import com.okitoki.okchat.di.*
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
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

        initLogger()
    }

    private fun initLogger(){
        // Logger Stack trace을 PRINT_STACK_COUNT 개로 조절
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("OKLog")
            .methodCount(5)
            .build()

        // Release 빌드 시 로그 제거
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}