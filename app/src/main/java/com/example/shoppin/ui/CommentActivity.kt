package com.example.shoppin.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shoppin.R
import com.example.shoppin.adapter.CommentAdapter
import com.example.shoppin.core.parent.ParentActivity
import com.example.shoppin.data.local.CommentEntity
import com.example.shoppin.databinding.ActivityCommentBinding
import com.example.shoppin.utils.GlobalConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CommentActivity : ParentActivity<TestViewModel, ActivityCommentBinding>() {

    private var img: Int? = null
    private var idMessage: Int? = null
    private var messageComment: String? = null
    private var commentList: ArrayList<CommentEntity>?=null
    private lateinit var  commentMessage:CommentEntity


    private val adapter by lazy {
        CommentAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras

        if (extras != null) {
            img = extras.getInt(GlobalConstants.IMG)
            idMessage=extras.getInt(GlobalConstants.ID_MESSAGE
            )
            commentList= extras.getSerializable(GlobalConstants.LIST_COMMENT) as ArrayList<CommentEntity>?

        }

        binding.apply {
            img?.let { bannerImg.setBackgroundResource(it) }
            setupListData()
            commentEdit.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    messageComment = if (s!!.isNotEmpty()){
                        s.toString()

                    }else{
                        ""

                    }

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })






            addCommentBtn.setOnClickListener {
                if (messageComment!=""){
                    commentMessage= CommentEntity(0,messageComment!!)
                    commentList!!.add(commentMessage)
                    viewModel.insertCommentList(commentMessage)
                    viewModel.updateCommentMessage(commentList!!,idMessage!!)

                    setupListData()

                }else{
                    Toast.makeText(this@CommentActivity, getString(R.string.enter_message), Toast.LENGTH_SHORT).show()
                }





            }
        }

    }

    override fun getResourceLayoutId(): Int = R.layout.activity_comment

    override fun getViewModelClass(): Class<TestViewModel> = TestViewModel::class.java

    private fun setupListData() {

        binding.recycler.adapter = adapter
        adapter.submitList(commentList)
        binding.recycler.addItemDecoration(
            DividerItemDecoration(
                binding.recycler.context,
                DividerItemDecoration.VERTICAL
            )
        )


    }


}
