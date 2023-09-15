package com.example.shoppin.ui

import android.os.Bundle
import android.view.View
import com.example.shoppin.R
import com.example.shoppin.core.parent.ParentFragment
import com.example.shoppin.databinding.FragmentWalletBinding

class WalletFragment : ParentFragment<MainViewModel, FragmentWalletBinding>() {

    override fun getResourceLayoutId(): Int = R.layout.fragment_wallet

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.apply {

        }


    }


}
