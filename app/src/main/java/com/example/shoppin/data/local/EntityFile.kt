package com.example.shoppin.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppin.data.local.DatabaseConstants.Companion.BANK_TABLE
import com.example.shoppin.data.local.DatabaseConstants.Companion.TRANSACTION_TABLE


@Entity(tableName = BANK_TABLE)
data class BankEntity(
    @PrimaryKey val code: String = "",
    val name: String = "",
    val logoUrl: Int = 0)


@Entity(tableName = TRANSACTION_TABLE)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: String = "",
    val cardNo:String="",
    val  date:Long=0,
    val refId:String="")




