package com.okitoki.okchat.di

import com.okitoki.okchat.viewmodel.SearchViewModel
import com.okitoki.okchat.viewmodel.BookmarkViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * @author ridsync
 */
val viewModelModule = module {
    viewModel { SearchViewModel(get(), get()) }
    viewModel { BookmarkViewModel(get()) }
}