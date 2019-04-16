package com.okitoki.okchat.util

import androidx.lifecycle.MutableLiveData

/**
 * @author ridsync
 */
class NotNullMutableLiveData<T : Any>(defaultValue: T) : MutableLiveData<T>() {

    init {
        value = defaultValue
    }

    override fun getValue()  = super.getValue()!!
}