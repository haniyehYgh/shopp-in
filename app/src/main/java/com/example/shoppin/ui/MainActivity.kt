package com.example.shoppin.ui

import android.os.Bundle
import com.example.shoppin.R
import com.example.shoppin.core.parent.ParentActivity
import com.example.shoppin.databinding.ActivityMain2Binding

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ParentActivity<MainViewModel, ActivityMain2Binding>() {


    override fun getResourceLayoutId(): Int = R.layout.activity_main2

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {


        }

    }








}
