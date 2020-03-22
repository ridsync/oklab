package com.okitoki.okchat.data.net.api

import androidx.lifecycle.LiveData
import com.okitoki.okchat.data.net.response.RepositoriesResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author ridsync
 */
interface AuthAPI {

    @GET("/auth/checkserver")
    fun authCheckserver(@QueryMap params: MutableMap<String, String>): Single<Response<RepositoriesResponse>>

    @GET("/auth/checkmail")
    fun authCheckMail(@QueryMap params: MutableMap<String, String>): Single<Response<RepositoriesResponse>>


}