package com.example.trek.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.trek.R
import com.example.trek.databinding.ActivitySchedulerBinding
import com.example.trek.model.PlacesItem
import com.example.trek.model.Schedule
import com.example.trek.repository.Repository
import com.example.trek.room.ExchangeDatabase
import com.example.trek.viewModel.HomeViewModel
import com.example.trek.viewModel.HomeViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class Scheduler : AppCompatActivity() {
    private lateinit var database: ExchangeDatabase
    private lateinit var viewModel:HomeViewModel
    private var startDate:Long = 0L
    private var endDate:Long = 0L
    private lateinit var place:PlacesItem
    private lateinit var binding: ActivitySchedulerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchedulerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        place = intent.getParcelableExtra<PlacesItem>("place")!!
        database = ExchangeDatabase.getDatabase(this)
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)

        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val dateBuilder = MaterialDatePicker.Builder.datePicker()
        dateBuilder.setTitleText("Select Date")
        val startPicker = dateBuilder.build()
        binding.dateStartPicker.setOnClickListener(View.OnClickListener {
            startPicker.show(supportFragmentManager,"Date_Picker")
        })

        startPicker.addOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener {
            startDate = it
            calendar.time = Date(it)
            binding.dateStartText.text = "${calendar.get(Calendar.DAY_OF_MONTH)}/" +
                    "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
        })

        val endPicker = dateBuilder.build()
        binding.dateEndPicker.setOnClickListener(View.OnClickListener {
            endPicker.show(supportFragmentManager,"Date_Picker")
        })

        endPicker.addOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener {
            endDate = it
            calendar.time = Date(it)
            binding.dateEndText.text = "${calendar.get(Calendar.DAY_OF_MONTH)}/" +
                    "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
        })

        val timePicker = MaterialTimePicker.Builder().
                            setTimeFormat(TimeFormat.CLOCK_12H).
                            setHour(12).
                            setMinute(0).
                            setTitleText("Select Time").
                            build()
        binding.timePicker.setOnClickListener(View.OnClickListener {
            timePicker.show(supportFragmentManager,"time_picker")
        })

        timePicker.addOnPositiveButtonClickListener{
            binding.timeText.text = "${timePicker.hour}:${timePicker.minute}"
            binding.timeState.text = if(timePicker.hour >= 12) "PM" else "AM"
        }

        binding.addScheduleBtn.setOnClickListener(View.OnClickListener {
            val sch = Schedule(binding.isNotify.isChecked,binding.name.text.toString(),startDate,endDate,null,place)
            viewModel.addSchedule(database,sch)
            finish()
        })
        updateUi()
    }

    private fun updateUi(){
        Glide.with(this).
            load(place.thumbnailUrl).
            into(binding.schedulerImg)
        binding.name.text = place.name
    }
}