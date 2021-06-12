package com.example.trek.activities

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.trek.R
import com.example.trek.databinding.ActivityWeatherBinding
import com.example.trek.fragments.mainFrag.WeatherForecastFragment
import com.example.trek.model.Forecast
import com.example.trek.repository.Repository
import com.example.trek.room.ExchangeDatabase
import com.example.trek.viewModel.HomeViewModel
import com.example.trek.viewModel.HomeViewModelFactory
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class WeatherActivity : AppCompatActivity(){

    private lateinit var dataBase: ExchangeDatabase
    private lateinit var viewModel: HomeViewModel
    private var hourlyWeather:ArrayList<Double> = arrayListOf()
    private var array:ArrayList<Entry> = arrayListOf()
    private lateinit var arr:Forecast
    private lateinit var backgroundColor:Map<String,Int>
    private lateinit var binding: ActivityWeatherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase = ExchangeDatabase.getDatabase(this)
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)

        backgroundColor = mapOf(
            "Sunny" to R.color.clear,
            "Clear" to R.color.clear,
            "Partly cloudy" to R.color.partlyCloudy,
            "Overcast" to R.color.overCast,
            "Cloudy" to R.color.cloudy,
            "Mist" to R.color.mist,
        )


        binding.weatherBackBtn.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        binding.additional.visibility = View.GONE

        binding.additionalParent.setOnClickListener(View.OnClickListener {
            if(binding.additional.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(binding.parentCard,AutoTransition())
                binding.additional.visibility = View.VISIBLE
            }
            else{
                TransitionManager.beginDelayedTransition(binding.parentCard,AutoTransition())
                binding.additional.visibility = View.GONE
            }
        })

        binding.forecastBtn.setOnClickListener(View.OnClickListener {
            val forecast = WeatherForecastFragment()
            val bundle = Bundle()
            bundle.putParcelable("forecast",arr)
            forecast.arguments = bundle
            forecast.show(supportFragmentManager,"forecastSheet")

            Log.d("array", "onCreate: $arr")

        })

        binding.update.setOnClickListener(View.OnClickListener {
            update(binding.location.text.toString())
            setData()
        })
        setData()
        deleteAllWeather()
        val handler = Handler()
        handler.postDelayed(Runnable {
        },500)
    }

    fun update(name:String){
        viewModel.getWeather(name,10,"yes","yes")
        viewModel.responseWeather.observe(this, Observer {
            viewModel.addWeather(dataBase,it)
        })
    }

    fun setData(){
        viewModel.readAllWeather(dataBase).observe(this, Observer {
            if(it != null){
                val tmp = "${it.current?.tempC.toString()}°"
                val feelLike = "${it.current?.feelslikeC.toString()}°"
                binding.linearLayout.setBackgroundColor(getColor(backgroundColor.get(it.current?.condition?.text)!!))
                if(it.current?.condition?.text == "Mist"){
                    binding.WeatherTemp.setTextColor(getColor(R.color.grey))
                    binding.feelsLikeTemp.setTextColor(getColor(R.color.grey))
                    binding.condition.setTextColor(getColor(R.color.grey))
                    binding.isDay.setTextColor(getColor(R.color.grey))
                    binding.feelsLikeText.setTextColor(getColor(R.color.grey))
                } //            binding.linearLayout.setBackgroundColor(getColor(R.color.grey))
                binding.WeatherTemp.text = tmp
                binding.feelsLikeTemp.text = feelLike
                binding.condition.text = it.current?.condition?.text
                if(it.current?.isDay != 0) binding.isDay.text = "morning" else binding.isDay.text = "night"

                binding.windSpeed.text = it.current?.windKph.toString()+"kmph"
                binding.humidity.text = it.current?.humidity.toString()
                binding.visibility.text = it.current?.visKm.toString()+"km"

                binding.windDir.text = it.current?.windDir
                binding.UV.text = it.current?.uv.toString()
                binding.date.text = it.location.localtime

                Glide.with(this).
                load("https:"+it.current?.condition?.icon).
                into(binding.weatherIcon)
                arr = it.forecast!!

                binding.pm25.text = String.format("%.1f", it.current?.airQuality?.pm25)
                binding.pm10.text = String.format("%.1f", it.current?.airQuality?.pm10)
                binding.so2.text = String.format("%.1f", it.current?.airQuality?.so2)
                binding.no2.text = String.format("%.1f", it.current?.airQuality?.no2)
                binding.o3.text = String.format("%.1f", it.current?.airQuality?.o3)
                binding.co.text = String.format("%.1f", it.current?.airQuality?.co?.div(100))

                if(it.current?.airQuality?.pm25!! < 90 && it.current.airQuality.pm10!! < 200 &&
                        it.current.airQuality.so2!! < 380 && it.current.airQuality.no2!! < 180 &&
                        it.current.airQuality.o3!! < 170 && it.current.airQuality.co?.div(100)!! < 10){
                    binding.pm25.setTextColor(getColor(R.color.success))
                    binding.pm10.setTextColor(getColor(R.color.success))
                    binding.so2.setTextColor(getColor(R.color.success))
                    binding.no2.setTextColor(getColor(R.color.success))
                    binding.o3.setTextColor(getColor(R.color.success))
                    binding.co.setTextColor(getColor(R.color.success))
                }
                else{
                    binding.pm25.setTextColor(getColor(R.color.warning))
                    binding.pm10.setTextColor(getColor(R.color.warning))
                    binding.so2.setTextColor(getColor(R.color.warning))
                    binding.no2.setTextColor(getColor(R.color.warning))
                    binding.o3.setTextColor(getColor(R.color.warning))
                    binding.co.setTextColor(getColor(R.color.warning))
                }
                chart()
            }
        })
    }

    fun deleteAllWeather(){
        viewModel.deleteAll(dataBase)
    }

    fun chart(){
        val lineChart:LineChart = binding.chart
        var xA = 0f
        val xAxislabel:ArrayList<Int> = arrayListOf()

        for(i in arr.forecastday?.get(0)?.hour!!){
            array.add(Entry(xA,i?.tempC?.toFloat()!!))
            xAxislabel.add(xA.toInt())
            xA += 1f
        }

        val dataSet = LineDataSet(array,"chart")
        dataSet.color = getColor(R.color.otherGrey)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 14f
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.highLightColor = getColor(R.color.red)
        dataSet.setDrawHorizontalHighlightIndicator(false)
        dataSet.setDrawVerticalHighlightIndicator(false)
        dataSet.setCircleColor(getColor(R.color.carbon_grey_800))
        dataSet.circleRadius = 5.5f
        dataSet.circleHoleRadius = 4f
        dataSet.setDrawFilled(true)
        dataSet.fillDrawable = getDrawable(R.drawable.clear_sky_background)

        val lineData = LineData(dataSet)

        lineChart.data = lineData
        lineChart.notifyDataSetChanged()
        lineChart.invalidate()
        lineChart.setVisibleXRangeMaximum(5f)
        lineChart.setTouchEnabled(true);
        lineChart.isClickable = false;
        lineChart.isDoubleTapToZoomEnabled = false;
        lineChart.isDoubleTapToZoomEnabled = false;

        lineChart.setDrawBorders(false);
        lineChart.setDrawGridBackground(false);

        lineChart.description.isEnabled = false;
        lineChart.legend.isEnabled = false;

        lineChart.axisLeft.setDrawGridLines(false);
        lineChart.axisLeft.setDrawLabels(false);
        lineChart.axisLeft.setDrawAxisLine(false);

        lineChart.xAxis.setDrawGridLines(false);
        lineChart.xAxis.setDrawLabels(true);
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.xAxis.setDrawAxisLine(false);
        lineChart.axisRight.setDrawGridLines(false);
        lineChart.axisRight.setDrawLabels(false);
        lineChart.axisRight.setDrawAxisLine(false);
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}