package com.example.ilinktestapp.ui.load_images

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ilinktestapp.db.Image
import com.example.ilinktestapp.db.ImageDao
import com.example.ilinktestapp.di.AppComponent
import com.example.ilinktestapp.adapters.ImageRecyclerViewAdapter
import javax.inject.Inject

class LoadImagesViewModel : ViewModel() {

    fun injectDI(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    val images : LiveData<List<Image>> by lazy {
        imageDao.getAllImages()
    }

    @Inject lateinit var linerLayoutManager: LinearLayoutManager
    @Inject lateinit var imageRecyclerViewAdapter: ImageRecyclerViewAdapter
    @Inject lateinit var imageDao: ImageDao


    fun deleteAllImagesFromDB () {
        imageDao.deleteAllImages()
    }
}