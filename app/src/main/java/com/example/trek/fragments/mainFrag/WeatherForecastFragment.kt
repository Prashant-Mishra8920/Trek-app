package com.example.trek.fragments.mainFrag

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trek.adapter.ForecastAdapter
import com.example.trek.databinding.WeatherForecastFragmentBinding
import com.example.trek.model.Forecast
import com.example.trek.model.ForecastdayItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WeatherForecastFragment:BottomSheetDialogFragment() {
    private lateinit var forecastAdapter: ForecastAdapter
    private lateinit var recyclerArray:List<ForecastdayItem?>
    private lateinit var binding: WeatherForecastFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherForecastFragmentBinding.inflate(inflater,container,false)

        val forecast:Forecast = arguments?.getParcelable("forecast")!!

        Log.d("forecstItem", "onCreateView: $forecast")
        recyclerArray = forecast.forecastday!!
        forecastAdapter = ForecastAdapter(requireContext(), recyclerArray)
        binding.forecastRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.forecastRecyclerView.setHasFixedSize(true)
        binding.forecastRecyclerView.adapter = forecastAdapter

        return binding.root
    }
}