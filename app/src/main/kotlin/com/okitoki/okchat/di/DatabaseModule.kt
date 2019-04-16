package com.okitoki.okchat.di

import com.okitoki.okchat.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * @author ridsync
 */
val roomModule = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single(createOnStart = false) { get<AppDatabase>().getBookmarkDao() }
}