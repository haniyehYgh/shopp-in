package com.example.shoppin.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.shoppin.R
import com.example.shoppin.core.parent.ParentFragment
import com.example.shoppin.databinding.FragmentWalletBinding
import com.example.shoppin.utils.addThousandSeparator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : ParentFragment<MainViewModel, FragmentWalletBinding>() {

    override fun getResourceLayoutId(): Int = R.layout.fragment_wallet

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)



        binding.apply {

            viewModel._balance.observe(viewLifecycleOwner) {
                walletBalanceValue.text = "${
                    addThousandSeparator(it.toString())
                } ${getString(R.string.rial)}"

            }
            chargeWalletLayout.setOnClickListener {
                findNavController().navigate(R.id.action_walletFragment_to_amountChargeWalletBS)

            }

            circulationWalletLayout.setOnClickListener {
                findNavController().navigate(R.id.action_walletFragment_to_circulationWalletFragment)

            }
            kharidLayout.setOnClickListener { showToast(getString(R.string.compelet_message)) }
        }


    }


}
