package com.okitoki.okchat.ui.viewmodel

import com.okitoki.okchat.data.net.domain.Repository
import com.okitoki.okchat.data.net.response.RepositoriesResponse
import com.okitoki.okchat.extension.with
import com.okitoki.okchat.repository.AuthRepository
import com.okitoki.okchat.ui.base.BaseViewModel
import com.okitoki.okchat.util.NotNullMutableLiveData
import com.orhanobut.logger.Logger

/**
 * @author ridsync
 */
class AuthViewModel(private val authRepository: AuthRepository) : BaseViewModel() {
    private var query: String = ""
        get() = if (field.isEmpty()) "MVVM" else field

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _items: NotNullMutableLiveData<List<Repository>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Repository>>
        get() = _items

    fun reqServerCheck() {
        val params = mutableMapOf<String, String>().apply {
            this["device_id"] = query
            this["device_type"] = "android"
            this["fcm_token"] = "stars"// TODO FCM push Token
        }

        Logger.d("reqServerCheck params %s", params)

        addToDisposable(authRepository.reqServerCheck(params).with()
            .doOnSubscribe { _refreshing.value = true }
            .doOnSuccess { _refreshing.value = false }
            .doOnError { _refreshing.value = false }
            .subscribe({
                val res = processBaseResponse(it)
                res?.let { res:RepositoriesResponse ->
                    _items.value = res.repositories
                }
            }, {
                doOnErrorCallback()
            }))
    }

}