package com.example.trek.api

import androidx.lifecycle.LiveData
import com.example.trek.model.*
import com.example.trek.viewModel.HomeViewModel
import com.squareup.okhttp.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

public interface placesApi {

    @GET("https://mocki.io/v1/53bea98a-1f16-4cf3-ae91-8a64cf9584a0")
    suspend fun getFakePlace():Place

    @GET("https://mocki.io/v1/943212c3-d236-49d9-8c9b-f17f31a8cff4")
    suspend fun getRestaurant():Place

    @GET("https://mocki.io/v1/3eee6382-91c0-49d1-a26a-3b7004bb74fa")
    suspend fun getTransport():Place

    @GET("https://mocki.io/v1/25604104-c728-436d-8414-06f51dc06166")
    suspend fun getHotel():Place

    @GET("https://mocki.io/v1/a3ee0556-fbdb-4311-980c-d9da312d7271")
    suspend fun getPlay():Place

    @GET("places/list")
    suspend fun getPlace(@Header("x-api-key") api_key: String,@Query("parents") string:String):Place

    @GET("places/list")
    suspend fun getPlaceWithQuery(@Header("x-api-key") api_key: String,
                                  @Query("query") queryString:String,
                                  @Query("rating") rating:String):Place

    @GET("https://mocki.io/v1/2c7e3df9-08ee-440f-9329-318f05c453e5")
    suspend fun getPlaceWithId():IdPlace

    @GET("https://www.mapquestapi.com/directions/v2/route?key=k7J35AqYGiShKy2rSrV4G9gIrWrzHbyC")
    suspend fun getDirection(@Query("from")origin:String,@Query("to")destination:String):Direction

    @GET("https://v6.exchangerate-api.com/v6/111f9dd3bb7a764c0d7d84fe/latest/{currency}")
    suspend fun getExchangeRate(@Path("currency")currency:String):Exchange

    @GET("https://api.weatherapi.com/v1/forecast.json?key=6cf0baf89e76459ca64141307210406")
    suspend fun getWeather(@Query("q")q:String,
                           @Query("days")days:Int,
                           @Query("aqi")aqi:String,
                           @Query("alerts")alerts:String):WeatherForecast

}

