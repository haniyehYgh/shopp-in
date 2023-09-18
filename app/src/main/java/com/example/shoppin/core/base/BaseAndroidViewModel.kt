package com.example.shoppin.core.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {

    private var errorHandler = MutableLiveData<String>()
    open fun getNetworkErrors(): LiveData<String> = errorHandler



}