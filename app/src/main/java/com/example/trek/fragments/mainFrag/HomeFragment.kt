package com.example.trek.fragments.mainFrag

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trek.R
import com.example.trek.activities.ExchangeActivity
import com.example.trek.activities.ScheduleActivity
import com.example.trek.activities.TranslateActivity
import com.example.trek.activities.WeatherActivity
import com.example.trek.adapter.placesAdapter
import com.example.trek.databinding.FragmentHomeBinding
import com.example.trek.model.Place
import com.example.trek.model.PlacesItem
import com.example.trek.model.User
import com.example.trek.repository.Repository
import com.example.trek.viewModel.HomeViewModel
import com.example.trek.viewModel.HomeViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source


class HomeFragment : Fragment(),View.OnClickListener{

    private lateinit var documentReference: DocumentReference
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel:HomeViewModel
//    private var pl:ArrayList<*> = arrayListOf()
    private var places:ArrayList<PlacesItem> = arrayListOf()
    private lateinit var placeAdapter: placesAdapter
    private var stateIn = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
//        getLocation()


//        places.add(PlacesItem(viewType = 1))
//        setRecyclerView()

//        val repository = Repository()
//        val viewModelFactory = HomeViewModelFactory(repository)
//        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
//        viewModel.getPlace("country:55")
//        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
//            for(place in it.data.places){
//                Log.d("Response", "onCreateView: ${place.name}")
//                Log.d("Response", "onCreateView: ${place.perex}\n")
//                places.add(place)
//            }
//            placeAdapter = placesAdapter(requireContext(),places)
//            binding.homeRecyclerView.adapter = placeAdapter
//            binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        })

        if(isNetworkAvailable(requireContext())){
            binding.internetText.visibility = View.GONE
            Log.d("Connection", "onCreateView: Connected")
            placesGet()
        }
        else{
            binding.internetText.visibility = View.VISIBLE
            placeAdapter = placesAdapter(requireContext(),places)
            binding.homeRecyclerView.adapter = placeAdapter
            binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            Log.d("Connection", "onCreateView: Not Connected")
        }
        updateUi()

        binding.restaurantCard.setOnClickListener(this)
        binding.transportCard.setOnClickListener(this)
        binding.hotelCard.setOnClickListener(this)
        binding.playCard.setOnClickListener(this)
        binding.translateCard.setOnClickListener(this)
        binding.exchangeCard.setOnClickListener(this)
        binding.scheduleCard.setOnClickListener(this)
        binding.weatherCard.setOnClickListener(this)
        binding.cardHome.setOnClickListener(this)

        return binding.root
    }


    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        }
    }

    private fun placesGet(){
        viewModel.getFakePlace()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
            response(it)
        })
    }

    private fun restaurant(){
        viewModel.getRestaurant()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
            response(it)
        })
    }

    private fun transport(){
        viewModel.getTransport()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
            response(it)
        })
    }

    private fun hotel(){
        viewModel.getHotel()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
            response(it)
        })
    }

    private fun play(){
        viewModel.getPlay()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
            response(it)
        })
    }

    private fun response(it:Place){
        places.clear()
        for(place in it.data?.places!!){
            if(place?.thumbnailUrl != null){
                places.add(place)
            }
        }
        placeAdapter = placesAdapter(requireContext(),places)
        binding.homeRecyclerView.adapter = placeAdapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    fun updateUi(){
        val animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
        if(stateIn == 0){
            binding.cardHome.startAnimation(animation)
            binding.cardHome.setOnClickListener(View.OnClickListener {
                Log.d("tag", "onCreateView: selected")
            })
            stateIn = 1
        }

        documentReference = FirebaseFirestore.getInstance().
        collection("Trek").
        document(firebaseUser.uid).
        collection("userData").
        document("data")
        val source = Source.CACHE
        documentReference.get(source).addOnSuccessListener {
            val user = it.toObject(User::class.java)
            binding.UserName.text = user?.name

        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.restaurantCard.id -> restaurant()

            binding.transportCard.id -> transport()

            binding.hotelCard.id -> hotel()

            binding.playCard.id -> play()

            binding.noteCancel.id -> {
                val anim = AnimationUtils.loadAnimation(context, R.anim.zoom_out)
                binding.cardHome.startAnimation(anim)
                binding.cardHome.visibility = View.GONE
            }

            binding.exchangeCard.id -> {
                val intent = Intent(context, ExchangeActivity::class.java)
                context?.startActivity(intent)
            }

            binding.translateCard.id -> {
                val intent = Intent(context, TranslateActivity::class.java)
                context?.startActivity(intent)
            }

            binding.scheduleCard.id -> {
                val intent = Intent(context, ScheduleActivity::class.java)
                context?.startActivity(intent)
            }

            binding.weatherCard.id -> {
                val intent = Intent(context, WeatherActivity::class.java)
                context?.startActivity(intent)
            }
        }
    }
}