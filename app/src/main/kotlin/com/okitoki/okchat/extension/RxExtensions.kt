package com.okitoki.okchat.extension

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author ridsync
 */
fun <T> Single<T>.with(): Single<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
