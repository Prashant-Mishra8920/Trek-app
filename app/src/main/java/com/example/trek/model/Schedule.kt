package com.example.trek.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Schedule_table")
@Parcelize
data class Schedule(

    val isNotifyOn: Boolean? = null,

    @PrimaryKey(autoGenerate = false)
    val name: String,

    val startDate: Long? = null,

    val endDate: Long? = null,

    val Budget : Double? = null,

    val place: PlacesItem? = null
) : Parcelable
