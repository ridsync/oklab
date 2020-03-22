package com.okitoki.okchat.di

import com.okitoki.okchat.ui.viewmodel.BookmarkViewModel
import com.okitoki.okchat.ui.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author ridsync
 */
val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { BookmarkViewModel(get()) }
}