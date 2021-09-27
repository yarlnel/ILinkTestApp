package com.example.ilinktestapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Image::class], version = 2, exportSchema = false)
@TypeConverters(value = [BitmapToStringTypeConvector::class])
abstract class MainDatabase : RoomDatabase() {
    abstract fun imageDao () : ImageDao
}