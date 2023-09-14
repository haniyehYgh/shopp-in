package com.example.shoppin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [MessageEntity::class,CommentEntity::class],
    version = 12,
    exportSchema = false
)
@TypeConverters(CommentTypeConvertor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}