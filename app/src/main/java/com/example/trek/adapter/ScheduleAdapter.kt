package com.example.trek.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trek.R
import com.example.trek.activities.ScheduleActivity
import com.example.trek.databinding.ScheduleCardBinding
import com.example.trek.model.Schedule
import com.example.trek.repository.Repository
import com.example.trek.room.ExchangeDatabase
import com.example.trek.viewModel.HomeViewModel
import com.example.trek.viewModel.HomeViewModelFactory
import kotlinx.coroutines.launch
import java.util.*

class ScheduleAdapter(context: Context,list: List<Schedule>): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    val con = context
    val li = list
    private lateinit var listener: OnClickListener

    interface OnClickListener{
        fun onItemLongClick(position: Int)
    }

    fun setOnItemLongClick(clickListener:OnClickListener){
        listener = clickListener
    }

    private lateinit var database: ExchangeDatabase
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ScheduleCardBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScheduleAdapter.ScheduleViewHolder {
        binding = ScheduleCardBinding.inflate(LayoutInflater.from(con),parent,false)
        return ScheduleViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ScheduleViewHolder, position: Int) {
        val sch = li[position]
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.time = Date(sch.startDate!!)
        binding.startDate.text = "${calendar.get(Calendar.DAY_OF_MONTH)}." +
                "${calendar.get(Calendar.MONTH) + 1}.${calendar.get(Calendar.YEAR)}"

        calendar.time = Date(sch.endDate!!)
        binding.endDate.text = "${calendar.get(Calendar.DAY_OF_MONTH)}." +
                "${calendar.get(Calendar.MONTH) + 1}.${calendar.get(Calendar.YEAR)}"

        if(sch.isNotifyOn!!){
            Glide.with(con).load(R.drawable.notification_icon).into(binding.notificationIcon)
        } else{
            Glide.with(con).load(R.drawable.notification_off_icon).into(binding.notificationIcon)
        }

        val diff = (Date(sch.endDate).time - Date(sch.startDate).time).div(1000*60*60*24)

        binding.noDays.text = "$diff days"
        binding.scheduleName.text = sch.place?.name
        Glide.with(con).
            load(sch.place?.thumbnailUrl).
            into(binding.scheduleImage)

        holder.clickLayout.setOnLongClickListener(View.OnLongClickListener {
            AlertDialog.Builder(con).
            setTitle("Delete Schedule").
            setMessage("Do you want to delete this schedule?").
            setPositiveButton("yes", DialogInterface.OnClickListener { dialog, which ->
                listener.onItemLongClick(position)
            }).
            setNegativeButton("no",null).
            show()
            return@OnLongClickListener true
        })
    }

    override fun getItemCount(): Int {
        return li.size
    }

    class ScheduleViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val clickLayout:LinearLayout = itemView.findViewById(R.id.clickLayout)
    }
}