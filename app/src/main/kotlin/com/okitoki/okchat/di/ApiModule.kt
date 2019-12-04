package com.okitoki.okchat.di

import com.okitoki.okchat.data.net.api.SearchAPI
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @author ridsync
 */
val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(SearchAPI::class.java) }
}