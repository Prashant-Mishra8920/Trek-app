package com.example.trek.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trek.R
import com.example.trek.activities.*
import com.example.trek.model.PlacesItem

const val state = 0
class placesAdapter(context: Context, list:ArrayList<PlacesItem>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val con = context
    private val li = list

    override fun getItemViewType(position: Int): Int {
        return if(li[position].viewType == 1){
            0
        } else{
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       if(viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.basic_home_layout,parent,false)
            return BasicViewHolder(view)
        } else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.location_card,parent,false)
             return placesViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == 0){
            (holder as BasicViewHolder).bind(li[position])
        }
        else{
            (holder as placesViewHolder).bind(li[position])
        }
    }

    override fun getItemCount(): Int {
        return li.size
    }

    class placesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var placeImage: ImageView = itemView.findViewById(R.id.placeImage)
        var placeName: TextView = itemView.findViewById(R.id.placeName)
        var placeDetail: TextView = itemView.findViewById(R.id.placeDetail)
        var cardParent: LinearLayout = itemView.findViewById(R.id.carParent)
        var placeCardImage: CardView = itemView.findViewById(R.id.placeCardImage)

        fun bind(placesItem: PlacesItem){
            placeName.text = placesItem.name
            placeDetail.text = placesItem.perex
            Glide.with(itemView.context)
                .load(placesItem.thumbnailUrl)
                .into(placeImage)

            cardParent.setOnClickListener(View.OnClickListener {
                Log.d("tag", "onBindViewHolder: clicked")
                val intent = Intent(it.context, ViewLocation::class.java)
                intent.putExtra("place",placesItem)
                val option:ActivityOptionsCompat = ActivityOptionsCompat.
                makeSceneTransitionAnimation(it.context as Activity,placeCardImage,
                    ViewCompat.getTransitionName(placeCardImage)!!
                )
                it.context.startActivity(intent,option.toBundle())
            })
        }
    }

    class BasicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(placesItem: PlacesItem){

        }
    }
}