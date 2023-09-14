package com.example.shoppin.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shoppin.data.local.DbRepository
import com.example.shoppin.data.local.MessageEntity
import javax.inject.Inject

class MessagePagingSource @Inject constructor(private  val  repository: DbRepository ):PagingSource
<Int,MessageEntity>() {
    override fun getRefreshKey(state: PagingState<Int, MessageEntity>): Int? {
     return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MessageEntity> {
       return try {

           val currentPage=params.key?:1
           val  response=repository.getAllMessageWithPage(currentPage)


           val data= response ?: emptyList()
           val responseData = mutableListOf<MessageEntity>()
           responseData.addAll(data)

           LoadResult.Page(data=responseData,

           prevKey = if(currentPage==1)null else -1,
           nextKey = if(response.size==0)null else currentPage.plus(1))

       }catch (e:java.lang.Exception){
           LoadResult.Error(e)
       }
    }
}