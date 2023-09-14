package com.example.shoppin.data.local

import androidx.room.*

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessageList(message: MessageEntity)

    @Query("Select * From message_table")
     fun getAllMessage():MutableList<MessageEntity>


    @Query("Select * From message_table WHERE  page Like:page")
    fun getMessageWithPage(page: Int):MutableList<MessageEntity>

    @Query("DELETE From message_table")
    fun getDeleteAll()

    @Query("UPDATE message_table SET `isLike` = :isLike , totalLike = :totalLike WHERE id = :id")
    fun updateLikeMessageList(isLike: Boolean,totalLike:Long,id:Int)

    @Query("UPDATE message_table SET `listComment` = :listComment  WHERE id = :id")
    fun updateCommentMessageList(listComment: List<CommentEntity>,id:Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommentList(comment: CommentEntity)











}
