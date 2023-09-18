package com.example.shoppin.data.local

import javax.inject.Inject

class DbRepository @Inject constructor(
    @BankDaoQualifier private val bankDao: BankDao,
    @TransactionDaoQualifier private val transactionDao: TransactionDao
) {
    fun insertMessage(entity: MutableList<BankEntity>)=bankDao.insertBankList(entity)
    fun getBankWithCode(code:String)=bankDao.getBankWithCode(code)
    fun getAllBanks()=bankDao.getAllBanks()

    fun insertTransaction(entity:TransactionEntity)=transactionDao.insertTransaction(entity)
    fun getLastTransaction()=transactionDao.getLatestTransactionEntity()
    fun getAllTransaction()=transactionDao.getAllTransaction()



}