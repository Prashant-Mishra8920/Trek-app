package com.example.trek.repository

import com.example.trek.model.*
import com.example.trek.room.ExchangeDatabase
//import com.example.trek.room.ExchangeDao
//import com.example.trek.room.ExchangeDatabase

class Repository {
    private val key = "AHbk8cNIHZ15xE3hIVDn24yDwshIn5aK4T3Qgk6e"

    suspend fun getPlace(string:String): Place {
        return RetrofitInstance.api.getPlace(key,string)
    }

    suspend fun getPlaceWithQuery(string:String,rating:String): Place {
        return RetrofitInstance.api.getPlaceWithQuery(key,string,rating)
    }

    suspend fun getPlaceWithId(string: String):IdPlace{
        return RetrofitInstance.api.getPlaceWithId()
    }

    suspend fun getFakePlace():Place{
        return RetrofitInstance.api.getFakePlace()
    }

    suspend fun getRestaurant():Place{
        return RetrofitInstance.api.getRestaurant()
    }

    suspend fun getTransport():Place{
        return RetrofitInstance.api.getTransport()
    }

    suspend fun getHotel():Place{
        return RetrofitInstance.api.getHotel()
    }

    suspend fun getPlay():Place{
        return RetrofitInstance.api.getPlay()
    }

    suspend fun getDirection(from:String,to:String): Direction {
        return RetrofitInstance.api.getDirection(from,to)
    }

    suspend fun getExchangeRate(currency:String):Exchange{
        return RetrofitInstance.api.getExchangeRate(currency)
    }

    suspend fun addExchange(db:ExchangeDatabase,exchange: Exchange){
        db.exchangeDao().addExchange(exchange)
    }

    fun readAllData(db:ExchangeDatabase) = db.exchangeDao().readAllData()

    suspend fun deleteAll(db: ExchangeDatabase){
        db.exchangeDao().deleteAll()
    }

    suspend fun getWeather(q:String,days:Int,aqi:String,alerts:String):WeatherForecast{
        return RetrofitInstance.api.getWeather(q,days,aqi,alerts)
    }

    suspend fun addWeather(db:ExchangeDatabase,weatherForecast: WeatherForecast){
        db.exchangeDao().addWeather(weatherForecast)
    }

    fun readAllWeather(db: ExchangeDatabase) = db.exchangeDao().readAllWeather()

    suspend fun deleteAllWeather(db: ExchangeDatabase){
        db.exchangeDao().deleteAllWeather()
    }

    suspend fun addSchedule(db:ExchangeDatabase,schedule: Schedule){
        db.exchangeDao().addSchedule(schedule)
    }

    fun readAllSchedule(db: ExchangeDatabase) = db.exchangeDao().readAllSchedule()

    suspend fun deleteSchedule(db:ExchangeDatabase,schedule:Schedule) = db.exchangeDao().deleteSchedule(schedule)

    suspend fun deleteAllSchedule(db: ExchangeDatabase){
        db.exchangeDao().deleteAllSchedule()
    }

}