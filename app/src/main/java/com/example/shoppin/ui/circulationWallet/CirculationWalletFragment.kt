package com.example.shoppin.ui.circulationWallet

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.shoppin.R
import com.example.shoppin.core.parent.ParentFragment
import com.example.shoppin.databinding.FragmentCirculationWalletBinding
import com.example.shoppin.ui.MainViewModel


class CirculationWalletFragment: ParentFragment<MainViewModel, FragmentCirculationWalletBinding>() {

    private val adapter by lazy {
        TransactionAdapter()
    }

    override fun getResourceLayoutId(): Int = R.layout.fragment_circulation_wallet

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            mainAppbarLayout.setonEndIconClickListener { activity!!.onBackPressed() }
            setupListTransaction()


        }



    }

    private fun setupListTransaction() {

        if (viewModel.getAllTransaction().isNotEmpty()) {
            binding.recycler.visibility= VISIBLE
            binding.emptyIm.visibility= GONE
            binding.recycler.adapter = adapter
            adapter.submitList(viewModel.getAllTransaction())
        }else{
            binding.recycler.visibility= GONE
            binding.emptyIm.visibility= VISIBLE

        }
    }
}