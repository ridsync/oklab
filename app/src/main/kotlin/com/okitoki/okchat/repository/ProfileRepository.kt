package com.okitoki.okchat.repository

import com.okitoki.okchat.data.db.dao.BookmarkDao
import com.okitoki.okchat.data.net.api.AuthAPI
import com.okitoki.okchat.data.net.response.RepositoriesResponse
import com.okitoki.okchat.util.AppExecutors
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by okc on 2019-12-07.
 */
class ProfileRepository constructor(
    private val appExecutors: AppExecutors,
    private val api: AuthAPI
) {

//    fun reqProfilDetail(param: MutableMap<String,String>):Single<Response<RepositoriesResponse>> {
//        return api.authCheckserver(param)
//    }
//
//    fun reqProfileEdit(param: MutableMap<String,String>):Single<Response<RepositoriesResponse>>{
//        return api.authCheckMail(param)
//    }

}
