package com.example.shoppin.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shoppin.data.local.DatabaseConstants.Companion.COMMENT_TABLE_NAME
import com.example.shoppin.data.local.DatabaseConstants.Companion.USER_TABLE_NAME
import kotlinx.parcelize.Parcelize


@Entity(tableName = USER_TABLE_NAME)
data class MessageEntity (

 @PrimaryKey(autoGenerate = true)
 val id: Int=0,
 val page:Int=1,
 val img: Int=0,
 val caption: String="",
 val  totalLike: Long=0,
 val  isLike: Boolean=false,
 @TypeConverters(CommentTypeConvertor::class)
 var listComment: List<CommentEntity> = listOf(),
 )


@Entity(tableName = COMMENT_TABLE_NAME)
@Parcelize
data class CommentEntity (
 @PrimaryKey(autoGenerate = true)
 val idComment: Int=0,
 val message: String="",
): Parcelable





