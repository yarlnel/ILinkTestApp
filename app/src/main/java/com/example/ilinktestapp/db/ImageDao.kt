package com.example.ilinktestapp.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface ImageDao {
    @Insert
    fun insertImage(vararg images: Image)

    @Query("SELECT * FROM IMAGE_DATA")
    fun getAllImages() : LiveData<List<Image>>

    @Query("DELETE FROM IMAGE_DATA")
    fun deleteAllImages()

    @Delete
    fun deleteImage(images: Image)
}