package com.example.trek.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.trek.R
import com.example.trek.databinding.ActivityViewLocationBinding
import com.example.trek.fragments.mainFrag.moreInfoFragment
import com.example.trek.model.Direction
import com.example.trek.model.PlacesItem
import com.example.trek.repository.Repository
import com.example.trek.viewModel.HomeViewModel
import com.example.trek.viewModel.HomeViewModelFactory
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class ViewLocation : AppCompatActivity() {

    private var userLat:Double = 0.0
    private var userLng:Double = 0.0
    private var state:Int = 0
    private var moreState:Int = 0
    private var place: PlacesItem? = null
    private var direction:Direction? = null
    private lateinit var binding: ActivityViewLocationBinding
    private lateinit var refrence:DocumentReference
    private lateinit var user:FirebaseUser
    val db = Firebase.firestore

    private lateinit var viewModel:HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        place = intent.extras?.getParcelable<PlacesItem>("place")
        user = FirebaseAuth.getInstance().currentUser!!
        refrence = db.collection("Trek").document(user.uid).collection("FavPlaces").document(place?.name!!)
        getLocation()
        updateUi(place)


        binding.more.setOnClickListener(View.OnClickListener {
            val moreSheet = moreInfoFragment()

            val bundle = Bundle()
            bundle.putParcelable("place",place)
            bundle.putParcelable("direction",direction)

            moreSheet.arguments = bundle
            moreSheet.show(supportFragmentManager,"Bottom Sheet")
        })

        binding.favBtn.setOnClickListener(View.OnClickListener {
            if(state == 0){
                binding.favBtn.setImageDrawable(resources.getDrawable(R.drawable.fav_icon))
                addfavPlace()
                state = 1

            }
            else{
                binding.favBtn.setImageDrawable(resources.getDrawable(R.drawable.fav_blank_icon))
                deleteFavPlace()
                state = 0
            }
        })

        binding.backBtn.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        binding.scheduleBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,Scheduler::class.java)
            intent.putExtra("place",place)
            startActivity(intent)
        })

        binding.getLocation.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this,MapActivity::class.java)
//            intent.putExtra("lat",place?.location?.lat)
//            intent.putExtra("lng",place?.location?.lng)
//            startActivity(intent)
            val intent = Intent(this, HereMapActivity::class.java)
            intent.putExtra("lat",place?.location?.lat)
            intent.putExtra("lng",place?.location?.lng)
            startActivity(intent)
        })

    }

    fun updateUi(place:PlacesItem?){
        binding.LocationName.text = place?.name
        binding.LocationOverview.text = place?.perex
        Glide.with(this)
            .load(place?.thumbnailUrl)
            .into(binding.LocationImage)

            refrence.get().addOnSuccessListener {
                Log.d("Document", "updateUi: ${it.data?.values}")
                if(it.data?.values != null){
                    val pl = it.toObject(PlacesItem::class.java)
                    if(pl?.isFav!!){
                        binding.favBtn.setImageDrawable(resources.getDrawable(R.drawable.fav_icon))
                        state = 1
                    }
                }
        }
    }


    private fun addfavPlace(){
        val name:String = place?.name!!
        val user = FirebaseAuth.getInstance().currentUser
        val db = Firebase.firestore

        place!!.isFav = true
        if (user != null) {
            db.collection("Trek").
            document(user.uid).
            collection("FavPlaces").
            document(name).set(place!!)
        }
    }

    fun deleteFavPlace(){
        refrence.delete().addOnCompleteListener(OnCompleteListener {
            if(it.isSuccessful){
                Log.d("Delete", "deleteFavPlace: Place deleted")
            }
        })
        onBackPressed()
    }

    private fun expense(){
        val destLat:Double = place?.location?.lat!!
        val destLng:Double = place?.location?.lng!!

        Log.d("LOCATIONEXPENSE", "expense: $destLat, $destLng")
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
        viewModel.getDirection("$userLat,$userLng","$destLat,$destLng")
        viewModel.responseDirection.observe(this, Observer {
            direction = it
            Log.d("Distance", "distance: ${it.route?.distance}")
            Log.d("Distance", "time: ${it.route?.time?.div(3600)}")

            val hours = it.route?.time?.div(3600)
            val minutes = (it.route?.time?.rem(3600))?.div(60)

            val distance = String.format("%.1f",it.route?.distance?.times(1.609344)) + "Kms"
            val time = hours.toString() + "hrs "+ minutes.toString()+"min"
            val expense = "Rs " + String.format("%.1f",((it.route?.distance?.times(1.609344))?.div(20))?.times(90))
            binding.Distance.text = distance
            binding.Time.text = time
            binding.Expense.text = expense
        })
    }

    fun getLocation(){
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                userLat = it.result.latitude
                userLng = it.result.longitude
                Log.d("LOCATION", "expense: ${it.result}")

                expense()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getLocation()
    }
}