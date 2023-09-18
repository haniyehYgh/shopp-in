package com.example.shoppin.di

import android.content.Context
import androidx.room.Room
import com.example.shoppin.data.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DbModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context)=Room.databaseBuilder(context,
        AppDatabase::class.java,DatabaseConstants.DATABASE_NAME).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    @BankDaoQualifier
    fun provideBankDao(db: AppDatabase) = db.bankDao()

    @Provides
    @Singleton
    @TransactionDaoQualifier
    fun provideTransactionDao(db: AppDatabase) = db.transactionDao()



    @Provides
    fun provideEntity()=BankEntity()


}