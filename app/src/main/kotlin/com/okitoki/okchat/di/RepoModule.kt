package com.okitoki.okchat.di

import com.okitoki.okchat.repository.SearchRepository
import com.okitoki.okchat.util.AppExecutors
import org.koin.dsl.module.module

/**
 * Created by okc on 2019-12-09.
 */
val repoModule = module {
    single { SearchRepository(get(),get(),get()) }
}
val executorModule = module {
    single { AppExecutors() }
}