package com.example.shoppin.data.local

import javax.inject.Inject

class DbRepository @Inject constructor( private  val dao:MessageDao) {
    fun insertMessage(entity: MessageEntity)=dao.insertMessageList(entity)
    fun getAllMessage()=dao.getAllMessage()
    fun getAllMessageWithPage(page:Int)=dao.getMessageWithPage(page)
    fun deleteMessage()=dao.getDeleteAll()
    fun updateLikeMessage(isLike:Boolean,totalLike:Long,id:Int)=dao.updateLikeMessageList(isLike,totalLike,id)
    fun updateCommentMessage(listComment: List<CommentEntity>,id:Int)=dao.updateCommentMessageList(listComment,id)
    fun insertComment(entity: CommentEntity)=dao.insertCommentList(entity)



}