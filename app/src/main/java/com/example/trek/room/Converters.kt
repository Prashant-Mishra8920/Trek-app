package com.example.trek.room

import androidx.room.TypeConverter
import com.example.trek.model.*
import com.google.gson.Gson
import com.google.gson.JsonParser

class Converters {
    @TypeConverter
    fun conToStr(conversionRates: ConversionRates): String {
        val gson = Gson()
        return gson.toJson(conversionRates)
    }

    @TypeConverter
    fun strToCon(string: String):ConversionRates{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,ConversionRates::class.java)
    }

    @TypeConverter
    fun curToStr(current: Current): String {
        val gson = Gson()
        return gson.toJson(current)
    }

    @TypeConverter
    fun strToCur(string: String):Current{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,Current::class.java)
    }

    @TypeConverter
    fun forecToStr(forecast: Forecast): String {
        val gson = Gson()
        return gson.toJson(forecast)
    }

    @TypeConverter
    fun strToForec(string: String):Forecast{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,Forecast::class.java)
    }


    @TypeConverter
    fun dayToStr(day: Day): String {
        val gson = Gson()
        return gson.toJson(day)
    }

    @TypeConverter
    fun strToDay(string: String):Day{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,Day::class.java)
    }

    @TypeConverter
    fun hourToStr(hourItem: HourItem): String {
        val gson = Gson()
        return gson.toJson(hourItem)
    }

    @TypeConverter
    fun strToHour(string: String):HourItem{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,HourItem::class.java)
    }

    @TypeConverter
    fun locToStr(location: Location): String {
        val gson = Gson()
        return gson.toJson(location)
    }

    @TypeConverter
    fun strToLoc(string: String):Location{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,Location::class.java)
    }

    @TypeConverter
    fun astroToStr(astro: Astro): String {
        val gson = Gson()
        return gson.toJson(astro)
    }

    @TypeConverter
    fun strToAstro(string: String):Astro{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,Astro::class.java)
    }

    @TypeConverter
    fun conditionToStr(condition: Condition): String {
        val gson = Gson()
        return gson.toJson(condition)
    }

    @TypeConverter
    fun strToCondition(string: String):Condition{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,Condition::class.java)
    }

    @TypeConverter
    fun forecastToStr(forecastdayItem: ForecastdayItem): String {
        val gson = Gson()
        return gson.toJson(forecastdayItem)
    }

    @TypeConverter
    fun strToforcast(string: String):ForecastdayItem{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,ForecastdayItem::class.java)
    }

    @TypeConverter
    fun alertsToStr(alerts: Alerts): String {
        val gson = Gson()
        return gson.toJson(alerts)
    }

    @TypeConverter
    fun strToAlerts(string: String):Alerts{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,Alerts::class.java)
    }


    @TypeConverter
    fun placeToStr(placesItem: PlacesItem): String {
        val gson = Gson()
        return gson.toJson(placesItem)
    }

    @TypeConverter
    fun strToPlace(string: String):PlacesItem{
        val gson = Gson()
        val json = JsonParser().parse(string)
        return gson.fromJson(json,PlacesItem::class.java)
    }


}