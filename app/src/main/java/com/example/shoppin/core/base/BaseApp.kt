package com.example.shoppin.core.base

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import timber.log.Timber


abstract class BaseApp : MultiDexApplication() {

    abstract fun logoutAndRestart()
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}