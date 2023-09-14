package com.example.shoppin.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.shoppin.R
import com.example.shoppin.adapter.LoadMoreAdapter
import com.example.shoppin.adapter.TestAdapter
import com.example.shoppin.core.parent.ParentActivity
import com.example.shoppin.data.local.CommentEntity

import com.example.shoppin.data.local.MessageEntity
import com.example.shoppin.databinding.ActivityTestBinding
import com.example.shoppin.utils.GlobalConstants
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable


@AndroidEntryPoint
class TestActivity : ParentActivity<TestViewModel, ActivityTestBinding>() {

    companion object {
        var like = false
    }

    private val adapter by lazy {
        TestAdapter(callbackLike = {
            like = if (like) {
                viewModel.updateLikeMessage(like, it.totalLike + 1, it.id)
                false
            } else {
                viewModel.updateLikeMessage(like, it.totalLike - 1, it.id)
                true
            }

        }, callbackComment = {
            val intent = Intent(applicationContext, CommentActivity::class.java)
            intent.putExtra(GlobalConstants.IMG, it.img)
            intent.putExtra(GlobalConstants.ID_MESSAGE, it.id)
            intent.putExtra(GlobalConstants.LIST_COMMENT, it.listComment as Serializable?)



            startActivity(intent)
            finish()


        })
    }


    private lateinit var message: MessageEntity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {

            setupListData()
        }

    }

    override fun getResourceLayoutId(): Int = R.layout.activity_test

    override fun getViewModelClass(): Class<TestViewModel> = TestViewModel::class.java


    override fun onShowLoading() {
        super.onShowLoading()
        if (!binding.loadingView.loadingUiRoot.isVisible) {
            binding.loadingView.loadingUiRoot.visibility = View.VISIBLE
        }
    }

    override fun onHideLoading() {
        super.onHideLoading()
        if (binding.loadingView.loadingUiRoot.isVisible) {
            binding.loadingView.loadingUiRoot.visibility = View.GONE
        }
    }


    private fun setupListData() {

        if (viewModel.getAllMessage().isNotEmpty()) {
            loadDataFromDb()
        } else {
            getFirstData()
        }
    }


    private fun getFirstData(){
        viewModel.deleteMessage()
        for (i in 0 until messageList().size) {
            message = MessageEntity(
                messageList()[i].id,
                messageList()[i].page,
                messageList()[i].img,
                messageList()[i].caption,
                messageList()[i].totalLike,
                messageList()[i].isLike,
                messageList()[i].listComment
            )
            viewModel.insertMessageList(message)
        }
        setupListData()
    }

    private  fun loadDataFromDb(){

        binding.recycler.adapter = adapter

        //load data
        lifecycleScope.launchWhenCreated {
            viewModel.messageList.collect {
                adapter.submitData(it)
            }
        }
        //loading
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect {
                val state = it.refresh
                if (state is LoadState.Loading) {
                    onShowLoading()
                } else {
                    onHideLoading()
                }
            }
        }
        //load more
        binding.recycler.adapter = adapter.withLoadStateFooter(
            LoadMoreAdapter()
        )
    }

    private fun messageList(): ArrayList<MessageEntity> {
        return object : ArrayList<MessageEntity>() {
            init {
                add(
                    MessageEntity(

                        0,
                        1,
                        R.drawable.camra,
                        "1جهت تست ایجاد شده است",
                        100,
                        false,
                        listOf(CommentEntity(1, "abc"), CommentEntity(2, "Vivek"))
                    )
                )
                add(
                    MessageEntity(

                        0,
                        1,
                        R.drawable.tree,
                        "2جهت تست ایجاد شده است",
                        100,
                        false,
                        listOf(CommentEntity(1, "abc"), CommentEntity(2, "Vivek"))
                    )
                )
                add(
                    MessageEntity(

                        0,
                        1,
                        R.drawable.tree,
                        "3جهت تست ایجاد شده است",
                        100,
                        false,
                        listOf(CommentEntity(1, "abc"))
                    )
                )
                add(
                    MessageEntity(

                        0,
                        1,
                        R.drawable.home,
                        "جهت تست ایجاد شده است4",
                        100,
                        false,
                        listOf(
                            CommentEntity(1, "abc"),
                            CommentEntity(2, "Vivek"),
                            CommentEntity(3, "Vivek")
                        )
                    )
                )

                add(
                    MessageEntity(

                        0,
                        1,
                        R.drawable.camra,
                        "جهت تست ایجاد شده است5",
                        100,
                        false,
                        listOf(
                            CommentEntity(1, "abc"),
                            CommentEntity(2, "Vivek"),
                            CommentEntity(3, "Vivek"),
                            CommentEntity(4, "Vivek")
                        )
                    )
                )
                add(
                    MessageEntity(

                        0,
                        1,
                        R.drawable.home,
                        "جهت تست ایجاد شده است6",
                        100,
                        false,
                        listOf(
                            CommentEntity(1, "abc"),
                            CommentEntity(2, "Vivek"),
                            CommentEntity(3, "Vivek"),
                            CommentEntity(4, "Vivek")
                        )
                    )
                )

                add(
                    MessageEntity(

                        0,
                        1,
                        R.drawable.tree,
                        "جهت تست ایجاد شده است7",
                        100,
                        false,
                        listOf(CommentEntity(1, "abc"), CommentEntity(2, "Vivek"))
                    )
                )

                add(
                    MessageEntity(

                        0,
                        1,
                        R.drawable.home,
                        "جهت تست ایجاد شده است8",
                        100,
                        false,
                        listOf(
                            CommentEntity(1, "abc"),
                            CommentEntity(2, "Vivek"),
                            CommentEntity(3, "Vivek")
                        )
                    )
                )

                add(
                    MessageEntity(

                        0,
                        1,
                        R.drawable.home,
                        "جهت تست ایجاد شده است9",
                        100,
                        false,
                        listOf(
                            CommentEntity(1, "abc"),
                            CommentEntity(2, "Vivek"),
                            CommentEntity(3, "Vivek"),
                            CommentEntity(4, "Vivek")
                        )
                    )
                )
                add(
                    MessageEntity(

                        0,
                        1,
                        R.drawable.tree,
                        "جهت تست ایجاد شده است10",
                        100,
                        false,
                        listOf(
                            CommentEntity(1, "abc"),
                            CommentEntity(2, "Vivek"),
                            CommentEntity(3, "Vivek"),
                            CommentEntity(4, "Vivek")
                        )
                    )
                )

                add(
                    MessageEntity(

                        0,
                        2,
                        R.drawable.tree,
                        "جهت تست ایجاد شده است11",
                        100,
                        false,
                        listOf(
                            CommentEntity(1, "abc"),
                            CommentEntity(2, "Vivek"),
                            CommentEntity(3, "Vivek"),
                            CommentEntity(4, "Vivek")
                        )
                    )
                )

                add(
                    MessageEntity(

                        0,
                        2,
                        R.drawable.camra,
                        "جهت تست ایجاد شده است12",
                        100,
                        false,
                        listOf(
                            CommentEntity(1, "abc"),
                            CommentEntity(2, "Vivek"),
                            CommentEntity(3, "Vivek"),
                            CommentEntity(4, "Vivek")
                        )
                    )
                )

                add(
                    MessageEntity(

                        0,
                        2,
                        R.drawable.camra,
                        "جهت تست ایجاد شده است13",
                        100,
                        false,
                        listOf(
                            CommentEntity(1, "abc"),
                            CommentEntity(2, "Vivek"),
                            CommentEntity(3, "Vivek"),
                            CommentEntity(4, "Vivek")
                        )
                    )
                )
            }

        }
    }



}
