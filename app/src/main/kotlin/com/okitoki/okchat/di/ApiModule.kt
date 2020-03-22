package com.okitoki.okchat.di

import com.okitoki.okchat.data.net.api.AuthAPI
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @author ridsync
 */
val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(AuthAPI::class.java) }
}