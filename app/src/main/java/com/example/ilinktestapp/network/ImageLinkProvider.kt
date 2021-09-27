package com.example.ilinktestapp.network

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImageLinkProvider
@Inject constructor(
    private val catApiService: CatApiService,
    private val duckApiService: DuckApiService,
    private val foxApiService: FoxApiService,
)
{
    private val _imageLinkProviderTag = ImageLinkProvider::class.java.simpleName
    private val logException = { t: Throwable ->
        Log.e(_imageLinkProviderTag, t.stackTraceToString())
    }

    private val _disposeBug = CompositeDisposable()

    fun clearDisposeBug () {
        _disposeBug.clear()
    }

    fun getFoxImageLink (callback: (String) -> Unit) {
        val disposable = foxApiService
            .getRandomFox()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({
                callback(it.image.toString())
            }, {
                logException(it)
            })
        _disposeBug.add(disposable)
    }

    fun getDuckImageLink(callback: (String) -> Unit) {
        val disposable = duckApiService
            .getRandomDuck()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({
                callback(it.imageUrl.toString())
            }, {
                logException(it)
            })
        _disposeBug.add(disposable)
    }

    fun getDogImageLink(callback: (String) -> Unit) {
        callback("https://place.dog/300/200")
    }

    fun getCatImageLink (callback: (String) -> Unit) {
        val disposable = catApiService
            .getRandomCat()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({
                callback(it.file.toString())
            }, {
               logException(it)
            })
        _disposeBug.add(disposable)
    }
}