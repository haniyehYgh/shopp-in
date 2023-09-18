package com.example.shoppin.data.local

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BankDaoQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TransactionDaoQualifier