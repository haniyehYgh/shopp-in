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
import com.example.shoppin.paging.MessagePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class TestViewModel @Inject constructor(
    application: Application,
    private val repositoryDb: DbRepository
) : BaseAndroidViewModel(application) {

    val messageList= Pager(PagingConfig(1)){
        MessagePagingSource(repositoryDb)
    }.flow.cachedIn(viewModelScope)


    fun  insertMessageList(message: MessageEntity){
        repositoryDb.insertMessage(message)
    }

    fun  getAllMessage():MutableList<MessageEntity>{
      return  repositoryDb.getAllMessage()
    }

    fun  deleteMessage(){
        repositoryDb.deleteMessage()
    }

    fun  updateLikeMessage(isLike:Boolean,totalLike:Long,id:Int){
        repositoryDb.updateLikeMessage(isLike,totalLike,id)
    }

    fun  insertCommentList(comment: CommentEntity){
        repositoryDb.insertComment(comment)
    }

    fun  updateCommentMessage(listComment: List<CommentEntity>,id:Int){
        repositoryDb.updateCommentMessage(listComment,id)
    }



}