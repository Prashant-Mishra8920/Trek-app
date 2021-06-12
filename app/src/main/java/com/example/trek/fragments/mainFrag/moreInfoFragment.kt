package com.example.trek.fragments.mainFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trek.databinding.MoreInfoCardBinding
import com.example.trek.model.PlacesItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class moreInfoFragment: BottomSheetDialogFragment() {

    private lateinit var binding: MoreInfoCardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MoreInfoCardBinding.inflate(layoutInflater,container,false)

        updateUi()
        return binding.root
    }

    fun updateUi(){
        val place:PlacesItem = arguments?.getParcelable("place")!!
        binding.moreName.text = place.name

//        val direction:Direction = arguments?.getParcelable("direction")!!
//
//        val hours = direction.route?.time?.div(3600)
//        val minutes = (direction.route?.time?.rem(3600))?.div(60)
//
//        val distance = String.format("%.1f",direction.route?.distance?.times(1.609344)) + "Kms"
//        val time = hours.toString() + "hrs "+ minutes.toString()+"min"
//        val expense = "Rs " + String.format("%.1f",((direction.route?.distance?.times(1.609344))?.div(20))?.times(90))
//        binding.Distance.text = distance
//        binding.Time.text = time
//        binding.Expense.text = expense
    }

}

