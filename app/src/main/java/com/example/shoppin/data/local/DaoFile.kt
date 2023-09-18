package com.example.shoppin.data.local

import androidx.room.*

@Dao
interface BankDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBankList(banks: MutableList<BankEntity>)
    @Query("Select * From bank_table WHERE  code Like:code")
    fun getBankWithCode(code: String):BankEntity

    @Query("Select * From bank_table")
    fun getAllBanks():MutableList<BankEntity>
}


@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(banks: TransactionEntity)

    @Query("Select * From transaction_table")
    fun getAllTransaction():MutableList<TransactionEntity>


    @Query("SELECT * FROM transaction_table ORDER BY id DESC LIMIT 1")
     fun getLatestTransactionEntity(): TransactionEntity
}