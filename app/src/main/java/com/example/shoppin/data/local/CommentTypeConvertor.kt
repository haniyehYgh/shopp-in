package com.example.shoppin.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class CommentTypeConvertor {

   private val gson = Gson()
    @TypeConverter
    fun jsonToCommentList(data: String?): List<CommentEntity> {
        return try {
            if (data == null) {
                Collections.emptyList()
            }else{
                val listType = object : TypeToken<List<CommentEntity>>() {

                }.type

                gson.fromJson<List<CommentEntity>>(data, listType)
            }
        }catch (e:java.lang.Exception){
            Collections.emptyList()
        }



    }

    @TypeConverter
    fun commentListToJson(someObjects: List<CommentEntity>): String {
        return gson.toJson(someObjects)
    }


}