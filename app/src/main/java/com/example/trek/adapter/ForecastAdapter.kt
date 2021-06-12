package com.example.trek.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trek.R
import com.example.trek.databinding.ForecastCardBinding
import com.example.trek.model.ForecastdayItem

class ForecastAdapter(context: Context,list:List<ForecastdayItem?>): RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {
    private val li = list
    private val con = context

    private lateinit var binding: ForecastCardBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastAdapter.ForecastViewHolder {
        binding = ForecastCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ForecastViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ForecastAdapter.ForecastViewHolder, position: Int) {
        val forecastDay = li[position]
        binding.date.text = forecastDay?.date
        binding.maxTemp.text = forecastDay?.day?.maxtempC.toString()+"°"
        binding.minTemp.text = forecastDay?.day?.mintempC.toString()+"°"
        binding.conditionCard.text = forecastDay?.day?.condition?.text

        Glide.with(con).
        load("https:"+forecastDay?.day?.condition?.icon).
        into(binding.cardIcon)
    }

    override fun getItemCount(): Int {
        return li.size
    }

    class ForecastViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val maxTemp:TextView = itemView.findViewById(R.id.maxTemp)
    }
}