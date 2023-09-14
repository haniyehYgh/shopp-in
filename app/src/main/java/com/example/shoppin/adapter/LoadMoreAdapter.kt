package com.example.shoppin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppin.databinding.ItemLoadMoreBinding

class LoadMoreAdapter:LoadStateAdapter<LoadMoreAdapter.ViewHolder>(){

    private  lateinit var binding:ItemLoadMoreBinding
    override fun onBindViewHolder(holder: LoadMoreAdapter.ViewHolder, loadState: LoadState) {
        holder.binData(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadMoreAdapter.ViewHolder {
        binding= ItemLoadMoreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    inner  class  ViewHolder():RecyclerView.ViewHolder(binding.root){
        fun binData(state:LoadState){
            binding.apply {
                loadMoreProgress.isVisible=state is LoadState.Loading
            }

        }

    }
}