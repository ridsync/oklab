package com.okitoki.okchat.data.net.api

import androidx.lifecycle.LiveData
import com.okitoki.okchat.data.net.response.RepositoriesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author ridsync
 */
interface SearchAPI {

    @GET("/search/repositories")
    fun search(@QueryMap params: MutableMap<String, String>): Single<RepositoriesResponse>

}