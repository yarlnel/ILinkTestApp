package com.example.ilinktestapp.ui.home


import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.lifecycle.*
import com.example.ilinktestapp.db.Image
import com.example.ilinktestapp.db.ImageDao
import com.example.ilinktestapp.di.AppComponent
import com.example.ilinktestapp.network.CatApiService
import com.example.ilinktestapp.network.DuckApiService
import com.example.ilinktestapp.network.FoxApiService
import com.example.ilinktestapp.network.ImageLinkProvider
import com.example.ilinktestapp.image_utils.ImageRoundedCornersTransformation
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import javax.inject.Inject


class HomeViewModel : ViewModel(), LifecycleObserver {

    private val _imageBitMap = MutableLiveData<Bitmap>()
    val imageBitmap : LiveData<Bitmap> = _imageBitMap

    fun injectDi(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    val imageSize : Int by lazy {
        Resources.getSystem().displayMetrics.widthPixels - 200
    }


    @Inject lateinit var imageLinkProvider: ImageLinkProvider
    @Inject lateinit var picasso: Picasso
    @Inject lateinit var roundedCornersTransformation: ImageRoundedCornersTransformation
    @Inject lateinit var imageDao: ImageDao

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy () {
        imageLinkProvider.clearDisposeBug()
    }


    private inner class LocalTarget : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmap?.let { _imageBitMap.postValue(it) }
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        }
    }

    private val localTarget = LocalTarget()


    fun pushCurrentImageIntoDB () {
        imageBitmap.value ?.let { currentBitmap ->
            imageDao.insertImage(
                Image(image = currentBitmap)
            )
        }
    }


    private fun setAnimalImage(url: String) {
        picasso
            .load(url)
            .resize(imageSize, imageSize)
            .transform(roundedCornersTransformation)
            .into(localTarget)
    }

    fun showRandomDuckImage () =
       imageLinkProvider.getDuckImageLink { link ->
           setAnimalImage(url = link)
       }


    fun showRandomFoxImage () =
        imageLinkProvider.getFoxImageLink { link ->
            setAnimalImage(url = link)
        }


    fun showRandomDogImage () =
        imageLinkProvider.getDogImageLink { link ->
            setAnimalImage(url = link)
        }


    fun showRandomCatImage () =
        imageLinkProvider.getCatImageLink { link ->
            setAnimalImage(url = link)
        }

}