package com.example.trek.repository

import com.example.trek.util.Constants.Companion.BASE_URL
import com.example.trek.api.placesApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:placesApi by lazy {
        retrofit.create(placesApi::class.java)
    }
}