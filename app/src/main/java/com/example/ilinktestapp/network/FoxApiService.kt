package com.example.ilinktestapp.network

import com.example.ilinktestapp.models.FoxResponse
import io.reactivex.Single
import retrofit2.http.GET

interface FoxApiService {
    @GET("floof")
    fun getRandomFox() : Single<FoxResponse>
}