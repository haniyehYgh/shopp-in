package com.example.shoppin.ui

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.shoppin.core.base.BaseAndroidViewModel
import com.example.shoppin.data.local.CommentEntity
import com.example.shoppin.data.local.DbRepository
import com.example.shoppin.data.local.MessageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repositoryDb: DbRepository
) : BaseAndroidViewModel(application) {




    fun  insertMessageList(message: MessageEntity){
        repositoryDb.insertMessage(message)
    }







}