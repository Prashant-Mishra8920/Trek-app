package com.example.trek.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trek.model.Exchange
import com.example.trek.model.Schedule
import com.example.trek.model.WeatherForecast

@Database(entities = [Exchange::class,WeatherForecast::class,Schedule::class],version = 9,exportSchema = false)
@TypeConverters(Converters::class)
abstract class ExchangeDatabase:RoomDatabase() {

    abstract fun exchangeDao():ExchangeDao

    companion object{
        @Volatile
        private var Instance:ExchangeDatabase? = null

        fun getDatabase(context: Context):ExchangeDatabase{
            val tempInstance = Instance
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExchangeDatabase::class.java,
                    "exchange_database"
                ).fallbackToDestructiveMigration()
                    .build()
                Instance = instance
                return instance
            }
        }
    }

}