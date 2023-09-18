package com.example.shoppin.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.shoppin.core.base.BaseAndroidViewModel
import com.example.shoppin.data.local.BankEntity
import com.example.shoppin.data.local.DbRepository
import com.example.shoppin.data.local.TransactionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repositoryDb: DbRepository
) : BaseAndroidViewModel(application) {


    var mBalance: MutableLiveData<Int> = MutableLiveData()
    val _balance: LiveData<Int> = mBalance
    init {
        this.mBalance.value=80000
    }


    fun  insertBankList(bankList: MutableList<BankEntity>){
        repositoryDb.insertMessage(bankList)
    }

    fun  getBankWithCode(codeBank:String):BankEntity{
        return repositoryDb.getBankWithCode(codeBank)
    }

    fun  getAllBanks():MutableList<BankEntity>{
        return repositoryDb.getAllBanks()
    }

    fun insertTransaction(transient: TransactionEntity){
        repositoryDb.insertTransaction(transient)
    }


    fun  getAllTransaction():MutableList<TransactionEntity>{
        return repositoryDb.getAllTransaction()
    }

    fun  getLastTransaction():TransactionEntity{
        return repositoryDb.getLastTransaction()
    }

}