package com.okitoki.okchat.repository

import androidx.lifecycle.LiveData
import com.okitoki.okchat.data.db.dao.BookmarkDao
import com.okitoki.okchat.data.db.entity.Bookmark
import com.okitoki.okchat.data.net.api.SearchAPI
import com.okitoki.okchat.data.net.response.RepositoriesResponse
import com.okitoki.okchat.util.AppExecutors
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by okc on 2019-12-07.
 */
class SearchRepository constructor(
    private val appExecutors: AppExecutors,
    private val api: SearchAPI,
    private val dao: BookmarkDao
) {

    fun search(param: MutableMap<String,String>):Single<Response<RepositoriesResponse>> {
        return api.search(param)
    }

    fun insertBookmark(bookmark: Bookmark){
        return dao.insert(bookmark)
    }

//    fun loadUser(login: String): LiveData<Resource<User>> {
//        return object : NetworkBoundResource<User, User>(appExecutors) {
//            override fun saveCallResult(item: User) {
//                userDao.insert(item)
//            }
//
//            override fun shouldFetch(data: User?) = data == null
//
//            override fun loadFromDb() = userDao.findByLogin(login)
//
//            override fun createCall() = githubService.getUser(login)
//        }.asLiveData()
//    }
}
