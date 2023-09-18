package com.example.shoppin.ui.circulationWallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppin.core.base.BaseHolder
import com.example.shoppin.data.local.TransactionEntity
import com.example.shoppin.databinding.ItemTransactionAdapterBinding
import com.example.shoppin.utils.addThousandSeparator
import com.example.shoppin.utils.getJalaliFormattedDate

class TransactionAdapter() : ListAdapter<TransactionEntity, TransactionAdapter.TestViewHolder>(object :
    DiffUtil.ItemCallback<TransactionEntity>() {
    override fun areItemsTheSame(oldItem: TransactionEntity, newItem: TransactionEntity): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: TransactionEntity, newItem: TransactionEntity): Boolean =
        oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TestViewHolder(ItemTransactionAdapterBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, position)

        }
    }

    inner class TestViewHolder(private val binding: ItemTransactionAdapterBinding) :
        BaseHolder<TransactionEntity>(binding) {
        override fun bind(value: TransactionEntity, position: Int) {
            binding.item = value
            binding.executePendingBindings()
            binding.amountDiscList.text="شارژ کیف به مبلغ"+" "+addThousandSeparator(value.amount)+" "+"ریال"
            binding.refNoDiscList.text="با شماره پیگیری"+" "+value.refId
            binding.amountList.text="+"+" "+ addThousandSeparator(value.amount)
          binding.dateList.text= getJalaliFormattedDate(value.date, true, true)






        }
    }

}