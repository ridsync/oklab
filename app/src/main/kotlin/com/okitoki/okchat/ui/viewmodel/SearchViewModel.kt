package com.okitoki.okchat.ui.viewmodel

import com.okitoki.okchat.data.db.dao.BookmarkDao
import com.okitoki.okchat.data.db.entity.Bookmark
import com.okitoki.okchat.data.net.domain.Repository
import com.okitoki.okchat.data.net.api.SearchAPI
import com.okitoki.okchat.extension.with
import com.okitoki.okchat.repository.SearchRepository
import com.okitoki.okchat.ui.base.BaseViewModel
import com.okitoki.okchat.util.NotNullMutableLiveData
import com.okitoki.okchat.util.ioThread

/**
 * @author ridsync
 */
class SearchViewModel(private val searchRepository: SearchRepository) : BaseViewModel() {
    private var query: String = ""
        get() = if (field.isEmpty()) "MVVM" else field

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _items: NotNullMutableLiveData<List<Repository>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Repository>>
        get() = _items

    fun doSearch() {
        val params = mutableMapOf<String, String>().apply {
            this["q"] = query
            this["sort"] = "stars"
        }

        addToDisposable(searchRepository.search(params).with()
            .doOnSubscribe { _refreshing.value = true }
            .doOnSuccess { _refreshing.value = false }
            .doOnError { _refreshing.value = false }
            .subscribe({
                _items.value = it.repositories
            }, {
                // handle errors
            }))
    }

    fun onQueryChange(query: CharSequence) {
        this.query = query.toString()
    }

    fun saveToBookmark(repository: Repository) = ioThread { searchRepository.insertBookmark(Bookmark.to(repository)) }
}