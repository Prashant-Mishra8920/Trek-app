package com.example.trek.activities

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trek.adapter.ScheduleAdapter
import com.example.trek.databinding.ActivityScheduleBinding
import com.example.trek.model.Schedule
import com.example.trek.repository.Repository
import com.example.trek.room.ExchangeDatabase
import com.example.trek.viewModel.HomeViewModel
import com.example.trek.viewModel.HomeViewModelFactory
import kotlinx.coroutines.launch

class ScheduleActivity : AppCompatActivity(),ScheduleAdapter.OnClickListener{

    private lateinit var viewModel:HomeViewModel
    private lateinit var database: ExchangeDatabase
    private lateinit var adapter: ScheduleAdapter
    private var array: ArrayList<Schedule> = arrayListOf()
    private lateinit var binding: ActivityScheduleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = ExchangeDatabase.getDatabase(this)
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)

        binding.scheduleBackBtn.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        viewModel.readAllSchedule(database).observe(this, Observer {
            for(i in it){
                array.add(i)
            }

            adapter = ScheduleAdapter(this,array)
            binding.scheduleRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.scheduleRecyclerView.adapter = adapter
            adapter.setOnItemLongClick(this)
        })

    }

    override fun onItemLongClick(position: Int) {
        lifecycleScope.launch {
            viewModel.deleteSchedule(database, array[position])
            array.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    }
}