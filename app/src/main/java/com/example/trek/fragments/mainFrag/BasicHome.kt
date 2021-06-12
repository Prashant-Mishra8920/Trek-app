package com.example.trek.fragments.mainFrag

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.trek.R
import com.example.trek.databinding.BasicHomeLayoutBinding
import com.example.trek.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source

class BasicHome:Fragment(){

    private lateinit var documentReference: DocumentReference
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var binding: BasicHomeLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasicHomeLayoutBinding.inflate(inflater,container,false)

//        firebaseUser = FirebaseAuth.getInstance().currentUser!!
//        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_in)
//        binding.cardHome.startAnimation(animation)
//        binding.cardHome.setOnClickListener(View.OnClickListener {
//            Log.d("tag", "onCreateView: selected")
//        })
//        binding.noteCancel.setOnClickListener(View.OnClickListener {
//            val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_out)
//            binding.cardHome.startAnimation(anim)
//            binding.cardHome.visibility = View.GONE
//        })

//        binding.translateCard.setOnClickListener(View.OnClickListener {
//            Log.d("card", "onClick: translateCard")
//        })
//        binding.exchangeCard.setOnClickListener(View.OnClickListener {
//            Log.d("card", "onClick: ExchangeCard")
//        })
//        binding.scheduleCard.setOnClickListener(View.OnClickListener {
//            Log.d("card", "onClick: scheduleCard")
//        })
//        binding.mapCard.setOnClickListener(View.OnClickListener {
//            Log.d("card", "onClick: mapCard")
//        })

        return binding.root
    }

//    override fun onClick(v: View?) {
//        if(v?.id == R.id.exchangeCard){
//            Log.d("card", "onClick: ExchangeCard")
//        }
////        when(v?.id){
////            R.id.exchangeCard -> Log.d("card", "onClick: ExchangeCard")
////            R.id.translateCard -> Log.d("card", "onClick: translateCard")
////            R.id.scheduleCard -> Log.d("card", "onClick: scheduleCard")
////            R.id.mapCard -> Log.d("card", "onClick: mapCard")
////        }
//    }
}