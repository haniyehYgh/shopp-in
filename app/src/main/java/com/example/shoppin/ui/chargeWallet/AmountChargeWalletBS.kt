package com.example.shoppin.ui.chargeWallet

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoppin.R
import com.example.shoppin.core.parent.ParentSharedBottomSheet
import com.example.shoppin.databinding.BsAmountChargeWalletBinding
import com.example.shoppin.extension.addSeparatorToEditText
import com.example.shoppin.extension.afterTextChangedFlow
import com.example.shoppin.ui.MainViewModel
import com.example.shoppin.utils.HumanReadableInteger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AmountChargeWalletBS : ParentSharedBottomSheet<MainViewModel, BsAmountChargeWalletBinding>() {

    override fun getResourceLayoutId(): Int = R.layout.bs_amount_charge_wallet
    override fun getTheme(): Int = R.style.BottomSheetStyle


    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.apply {


            amountEt.afterTextChangedFlow()
                .onEach { editable ->
                    if (editable!!.isNotEmpty()) {
                        addSeparatorToEditText(amountEt)
                        rialsNumberToLetters.text =
                            HumanReadableInteger.convertRialsNumberToLetters(
                                editable.toString().replace(",", "")
                            )
                        if (editable!!.length > 3) {
                            submitCb.setStateButton(true)
                        } else {
                            submitCb.setStateButton(false)
                        }

                    }

                }
                .launchIn(lifecycleScope)

            submitCb.setOnClickListener {
                hideKeyboard(view)
                findNavController().navigate(AmountChargeWalletBSDirections.actionAmountChargeWalletBSToCartChargeWalletBS(binding.amountEt.text.toString()))



            }


        }
    }

}


