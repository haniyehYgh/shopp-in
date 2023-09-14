package com.example.shoppin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppin.core.base.BaseHolder
import com.example.shoppin.data.local.CommentEntity

import com.example.shoppin.databinding.ItemCommentAdapterBinding

import dagger.hilt.android.qualifiers.ApplicationContext

class CommentAdapter() : ListAdapter<CommentEntity, CommentAdapter.CommentViewHolder>(object : DiffUtil.ItemCallback<CommentEntity>() {
    override fun areItemsTheSame(oldItem: CommentEntity, newItem: CommentEntity): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: CommentEntity, newItem: CommentEntity): Boolean =
        oldItem == newItem
}) {
    lateinit var context: ApplicationContext

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return CommentViewHolder(ItemCommentAdapterBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {

        getItem(position).let {
            it?.let { it1 -> holder.bind(it1, position) }


        }
    }

    inner class CommentViewHolder(private val binding: ItemCommentAdapterBinding) :
        BaseHolder<CommentEntity>(binding) {
        override fun bind(value: CommentEntity, position: Int) {
            binding.item = value
            binding.executePendingBindings()


        }

    }

}