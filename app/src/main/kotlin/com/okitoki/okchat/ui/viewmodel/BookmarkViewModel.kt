package com.okitoki.okchat.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.okitoki.okchat.data.db.dao.BookmarkDao
import com.okitoki.okchat.data.db.entity.Bookmark
import com.okitoki.okchat.ui.base.BaseViewModel
import com.okitoki.okchat.util.ioThread

/**
 * @author ridsync
 */
class BookmarkViewModel(private val dao: BookmarkDao) : BaseViewModel() {
    val items: LiveData<PagedList<Bookmark>> = LivePagedListBuilder(dao.findAll(),  /* page size */ 10).build()

    fun delete(bookmark: Bookmark) = ioThread { dao.delete(bookmark) }
}