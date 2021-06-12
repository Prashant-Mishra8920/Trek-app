package com.example.trek.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trek.model.Exchange
import com.example.trek.model.Schedule
import com.example.trek.model.WeatherForecast

@Dao
interface ExchangeDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExchange(exchange: Exchange)

    @Update
    suspend fun updateExchange(exchange: Exchange)

    @Query("SELECT * FROM Exchange_table")
    fun readAllData():LiveData<List<Exchange>>

    @Query("DELETE FROM Exchange_table")
    suspend fun deleteAll()



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeather(weatherForecast: WeatherForecast)

    @Query("SELECT * FROM Weather_table")
    fun readAllWeather():LiveData<WeatherForecast>

    @Query("DELETE FROM WEATHER_TABLE")
    suspend fun deleteAllWeather()



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSchedule(schedule: Schedule)

    @Query("SELECT * FROM Schedule_table")
    fun readAllSchedule():LiveData<List<Schedule>>

    @Delete
    suspend fun deleteSchedule(schedule: Schedule)

    @Query("DELETE FROM Schedule_table")
    suspend fun deleteAllSchedule()
}