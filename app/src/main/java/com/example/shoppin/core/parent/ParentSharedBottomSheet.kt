package com.example.shoppin.core.parent


import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.shoppin.R
import com.example.shoppin.core.base.BaseAndroidViewModel
import com.example.shoppin.core.base.BaseApp
import com.example.shoppin.core.base.BaseExpandedBottomSheet


abstract class ParentSharedBottomSheet<T : BaseAndroidViewModel, E : ViewDataBinding> : BaseExpandedBottomSheet<E>() {

    lateinit var viewModel: T

    abstract fun getViewModelClass(): Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[getViewModelClass()]

        viewModel.getNetworkErrors().observe(this) { status ->
            when (status) {
                getString(R.string.forbidden) -> (requireActivity().application as BaseApp).logoutAndRestart()
                else -> onShowError(status)
            }
        }

    }


}