package com.example.shoppin.ui.receipt

import android.os.Bundle
import android.view.View
import com.example.shoppin.R
import com.example.shoppin.core.parent.ParentFragment
import com.example.shoppin.databinding.FragmentReceiptBinding
import com.example.shoppin.ui.MainViewModel
import com.example.shoppin.utils.addThousandSeparator
import com.example.shoppin.utils.formatPanWithMask
import com.example.shoppin.utils.getJalaliFormattedDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceiptFragment : ParentFragment<MainViewModel, FragmentReceiptBinding>() {

    override fun getResourceLayoutId(): Int = R.layout.fragment_receipt

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            mainAppbarLayout.setonEndIconClickListener { activity!!.onBackPressed() }
            amountReceipt.text=addThousandSeparator(viewModel.getLastTransaction().amount)+" "+getString(R.string.rial)
            cardNoReceipt.text= formatPanWithMask(viewModel.getLastTransaction().cardNo)
            dateReceipt.text=getJalaliFormattedDate(viewModel.getLastTransaction().date, true, true)
            refNoReceipt.text=viewModel.getLastTransaction().refId

        }

    }
}