package com.example.trek.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trek.model.*
import com.example.trek.repository.Repository
import com.example.trek.room.ExchangeDatabase
//import com.example.trek.room.ExchangeDatabase
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository):ViewModel() {
    val myResponse:MutableLiveData<Place> = MutableLiveData()
    val responseWithQuery:MutableLiveData<Place> = MutableLiveData()
    val responseDirection:MutableLiveData<Direction> = MutableLiveData()
    val responseWithId:MutableLiveData<IdPlace> = MutableLiveData()
    val responseExchangeRate:MutableLiveData<Exchange> = MutableLiveData()
    val roomResponse:MutableLiveData<List<Exchange>> = MutableLiveData()
    val weatherRoomResponse:MutableLiveData<WeatherForecast> = MutableLiveData()
    val responseWeather:MutableLiveData<WeatherForecast> = MutableLiveData()




    fun getPlace(string:String){
        viewModelScope.launch{
            val response = repository.getPlace(string)
            myResponse.value = response
        }
    }

    fun getPlaceWithQuery(string:String,rating:String){
        viewModelScope.launch{
            val response = repository.getPlaceWithQuery(string,rating)
            responseWithQuery.value = response
        }
    }

    fun getPlaceWithId(string: String){
        viewModelScope.launch {
            val response = repository.getPlaceWithId(string)
            responseWithId.value = response
        }
    }

    fun getFakePlace(){
        viewModelScope.launch {
            val response = repository.getFakePlace()
            myResponse.value = response
        }
    }
    fun getTransport(){
        viewModelScope.launch {
            val response = repository.getTransport()
            myResponse.value = response
        }
    }

    fun getRestaurant(){
        viewModelScope.launch {
            val response = repository.getRestaurant()
            myResponse.value = response
        }
    }

    fun getHotel(){
        viewModelScope.launch {
            val response = repository.getHotel()
            myResponse.value = response
        }
    }

    fun getPlay(){
        viewModelScope.launch {
            val response = repository.getPlay()
            myResponse.value = response
        }
    }

    fun getDirection(from: String,to:String){
        viewModelScope.launch {
            val response = repository.getDirection(from,to)
            responseDirection.value = response
        }
    }

    fun getExchangeRate(currency:String){
        viewModelScope.launch {
            val response = repository.getExchangeRate(currency)
            responseExchangeRate.value = response
        }
    }

    fun addExchange(db: ExchangeDatabase, exchange: Exchange){
        viewModelScope.launch {
            repository.addExchange(db,exchange)
        }
    }

    fun readAllData(db:ExchangeDatabase) = repository.readAllData(db)

    fun deleteAll(db:ExchangeDatabase){
        viewModelScope.launch {
            repository.deleteAll(db)
        }
    }

    fun getWeather(q:String,days:Int,aqi:String,alerts:String){
        viewModelScope.launch {
            val response = repository.getWeather(q,days,aqi,alerts)
            responseWeather.value = response
        }
    }

    fun addWeather(db: ExchangeDatabase,weatherForecast: WeatherForecast){
        viewModelScope.launch {
            repository.addWeather(db,weatherForecast)
        }
    }
    fun readAllWeather(db: ExchangeDatabase) = repository.readAllWeather(db)
//            val response = repository.readAllWeather(db)
//            weatherRoomResponse.value = response

    fun deleteAllWeather(db: ExchangeDatabase){
        viewModelScope.launch {
            repository.deleteAllWeather(db)
        }
    }

    fun addSchedule(db: ExchangeDatabase,schedule: Schedule){
        viewModelScope.launch {
            repository.addSchedule(db,schedule)
        }
    }

    fun readAllSchedule(db: ExchangeDatabase) = repository.readAllSchedule(db)

    suspend fun deleteSchedule(db: ExchangeDatabase,schedule: Schedule){
        viewModelScope.launch {
            repository.deleteSchedule(db,schedule)
        }
    }

    fun deleteAllSchedule(db: ExchangeDatabase){
        viewModelScope.launch {
            repository.deleteAllSchedule(db)
        }
    }

}