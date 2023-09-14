package com.example.shoppin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.shoppin.R
import com.example.shoppin.core.base.BaseHolder
import com.example.shoppin.data.local.MessageEntity
import com.example.shoppin.databinding.ItemTestAdapterBinding
import com.example.shoppin.ui.TestActivity
import dagger.hilt.android.qualifiers.ApplicationContext


@Suppress("DEPRECATION")
class TestAdapter(
    val callbackLike: (MessageEntity) -> Unit,
    val callbackComment: (MessageEntity) -> Unit
) : PagingDataAdapter<MessageEntity, TestAdapter.TestViewHolder>(object :
    DiffUtil.ItemCallback<MessageEntity>() {
    override fun areItemsTheSame(oldItem: MessageEntity, newItem: MessageEntity): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: MessageEntity, newItem: MessageEntity): Boolean =
        oldItem == newItem
}) {
    lateinit var context: ApplicationContext

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return TestViewHolder(ItemTestAdapterBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {

        getItem(position).let {
            it?.let { it1 -> holder.bind(it1, position) }


        }
    }

    inner class TestViewHolder(private val binding: ItemTestAdapterBinding) :
        BaseHolder<MessageEntity>(binding) {
        override fun bind(value: MessageEntity, position: Int) {

            binding.item = value
            binding.executePendingBindings()


            if (value.isLike) {
                binding.likeImg.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_favorite_red))
            } else {
                binding.likeImg.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_favorite_border))
            }

            binding.bannerImg.setBackgroundResource(value.img)

            binding.commentCount.text = "تعداد کامنت${value.listComment.size}"
            binding.likeCount.text = "لایک شده توسط${value.totalLike}نفر"
            binding.commentLayout.setOnClickListener {
                callbackComment.invoke(value)
            }

            binding.likeLayout.setOnClickListener {

                if (TestActivity.like) {
                    binding.likeImg.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_favorite_red))
                } else {
                    binding.likeImg.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_favorite_border))
                }

                callbackLike.invoke(value)
            }

        }

    }

}