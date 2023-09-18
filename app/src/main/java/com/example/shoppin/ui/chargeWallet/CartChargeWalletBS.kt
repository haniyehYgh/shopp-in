package com.example.shoppin.ui.chargeWallet

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoppin.R
import com.example.shoppin.core.parent.ParentSharedBottomSheet
import com.example.shoppin.data.local.BankEntity
import com.example.shoppin.data.local.TransactionEntity
import com.example.shoppin.databinding.BsCartChargeWalletBinding
import com.example.shoppin.extension.afterTextChangedFlow
import com.example.shoppin.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID

@AndroidEntryPoint
class CartChargeWalletBS : ParentSharedBottomSheet<MainViewModel, BsCartChargeWalletBinding>() {

    var card:Boolean=false
    var cvv2:Boolean=false
    var pass:Boolean=false
    var day:Boolean=false
    var year:Boolean=false
    private lateinit var transactionEntity: TransactionEntity

    override fun getResourceLayoutId(): Int = R.layout.bs_cart_charge_wallet
    override fun getTheme(): Int = R.style.BottomSheetStyle


    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            CoroutineScope(Dispatchers.IO).launch {
                val banks = viewModel.getAllBanks()
                if (banks.isEmpty()) {
                    val banksToAdd = mutableListOf<BankEntity>()
                    banksToAdd.add(BankEntity("636214", getString(R.string.ayandeh), R.drawable.logo_ayandeh_bank))
                    banksToAdd.add(BankEntity("627412", getString(R.string.en_bank), R.drawable.logo_envovin_bank))
                    banksToAdd.add(BankEntity("639347", getString(R.string.pasargad), R.drawable.logo_pasargad_bank))
                    banksToAdd.add(BankEntity("589463", getString(R.string.refah), R.drawable.logo_refah_bank))
                    banksToAdd.add(BankEntity("502938", getString(R.string.day), R.drawable.logo_day_bank))
                    banksToAdd.add(BankEntity("621986", getString(R.string.saman), R.drawable.logo_saman_bank))
                    banksToAdd.add(BankEntity("589210", getString(R.string.sepah), R.drawable.logo_sepah_bank))
                    banksToAdd.add(BankEntity("505416", getString(R.string.gardeshgary), R.drawable.logo_gardeshgary_bank))
                    banksToAdd.add(BankEntity("603799", getString(R.string.meli), R.drawable.logo_meli_bank))
                    banksToAdd.add(BankEntity("504706", getString(R.string.shahr), R.drawable.logo_shahr_bank))
                    banksToAdd.add(BankEntity("628023", getString(R.string.maskan), R.drawable.logo_maskan_bank))
                    banksToAdd.add(BankEntity("603769", getString(R.string.saderat), R.drawable.logo_saderat_bank))
                    viewModel.insertBankList(banksToAdd)
                }
            }

            cartNumberEt.afterTextChangedFlow().onEach { editable ->

                if (editable!!.length >= 6) {
                    getLogoBank(editable.toString())
                } else if (editable.isEmpty()) {
                    getLogoBank("")
                }
                if (editable.length==16){
                    card=true
                    checkActiveSubmitBtn()
                }else{
                    card=false
                    checkActiveSubmitBtn()
                }

            }.launchIn(lifecycleScope)


            cvv2Et.afterTextChangedFlow().onEach { editable ->

                if (editable!!.length>=3){
                    cvv2=true
                    checkActiveSubmitBtn()
                }else{
                    cvv2=false
                    checkActiveSubmitBtn()
                }

            }.launchIn(lifecycleScope)

            passEt.afterTextChangedFlow().onEach { editable ->

                if (editable!!.length>=4){
                    pass=true
                    checkActiveSubmitBtn()
                }else{
                    pass=false
                    checkActiveSubmitBtn()
                }

            }.launchIn(lifecycleScope)

            dayEt.afterTextChangedFlow().onEach { editable ->

                if (editable!!.length==2){
                    day=true
                    checkActiveSubmitBtn()
                }else{
                    day=false
                    checkActiveSubmitBtn()
                }

            }.launchIn(lifecycleScope)

            yearEt.afterTextChangedFlow().onEach { editable ->

                if (editable!!.length==2){
                    year=true
                    checkActiveSubmitBtn()
                }else{
                    year=false
                    checkActiveSubmitBtn()
                }

            }.launchIn(lifecycleScope)


            timer.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {
                    var counter = 120
                    while (counter > 0) {
                        withContext(Dispatchers.Main) {
                            timer.isEnabled = false
                            timer.text = "زمان باقی مانده:$counter"
                        }
                        delay(1000)
                        counter--
                    }
                    withContext(Dispatchers.Main) {
                        timer.isEnabled = true
                        timer.text = getString(R.string.get_ramz_poya)
                    }
                }
            }

            backCb.setOnClickListener {
                activity!!.onBackPressed()
            }

            submitCb.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.mBalance.postValue((viewModel.mBalance.value)!! +(arguments!!.getString("amount")!!.replace(",","").toInt()))
                    transactionEntity= TransactionEntity(0,(arguments!!.getString("amount")!!.replace(",","")),cartNumberEt.text.toString(),System.currentTimeMillis(),UUID.randomUUID().toString().substring(0,7))
                        viewModel.insertTransaction(transactionEntity)

                    withContext(Dispatchers.Main) {
                        dismiss()
                        findNavController().navigate(R.id.action_cartChargeWalletBS_to_receiptFragment)
                    }
                }


            }
        }
    }

    private fun getLogoBank(code: String) = if (code != "") {
        var drawLogo: Int? = 0
        val bankEntity: BankEntity = viewModel.getBankWithCode(code.toString().substring(0, 6))
        drawLogo = if (bankEntity == null) {
            R.drawable.ic_card
        } else {
            viewModel.getBankWithCode(code.substring(0, 6)).logoUrl
        }

        binding.cartIm.setImageResource(drawLogo)

    } else {
        binding.cartIm.setImageResource(R.drawable.ic_card)
    }

    private  fun checkActiveSubmitBtn(){
        if (card&&cvv2&&pass&&day&&year){
           binding.submitCb.setStateButton(true)
        }else{
            binding.submitCb.setStateButton(false)
        }
    }


}
