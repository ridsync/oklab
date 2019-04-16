package com.okitoki.okchat.di

import com.okitoki.okchat.data.net.api.SearchAPI
import org.koin.dsl.module.module
import retrofit2.Retrofit

/**
 * @author ridsync
 */
val apiModule = module {
    single(createOnStart = false) { get<Retrofit>().create(SearchAPI::class.java) }
}