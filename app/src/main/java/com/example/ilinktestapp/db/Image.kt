package com.example.ilinktestapp.db

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_data")
data class Image(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "image_id")
        val id: Int = 0,

    @ColumnInfo(name = "image_bitmap") val image: Bitmap,
)