package com.example.trek.activities

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.trek.R
import com.example.trek.databinding.ActivityHereMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.here.sdk.core.Anchor2D
import com.here.sdk.core.GeoCoordinates
import com.here.sdk.core.LanguageCode
import com.here.sdk.mapview.*
import com.here.sdk.routing.RoutingEngine
import com.here.sdk.routing.Waypoint
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.ArrayList

class HereMapActivity : AppCompatActivity() {
    private var lat:Double = 0.0
    private var lng:Double = 0.0

    private lateinit var routingEngine: RoutingEngine
    private lateinit var binding: ActivityHereMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHereMapBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val data = intent.extras
        lat = data?.getDouble("lat")!!
        lng = data.getDouble("lng")

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.setOnReadyListener(MapView.OnReadyListener {
            Log.d("MapIsCreated", "onCreate: MapReady")
        })
        loadMapScene()
    }

    private fun loadMapScene(){
        val bitmap = AppCompatResources.getDrawable(applicationContext, R.drawable.google_pin)?.toBitmap()
        val mapImage = MapImageFactory.fromBitmap(bitmap!!)
        val anchor2D = Anchor2D(0.5, 1.0)
        val mapMarker = MapMarker(GeoCoordinates(lat,lng),mapImage,anchor2D)

        binding.mapView.mapScene.loadScene(MapScheme.NORMAL_DAY,MapScene.LoadSceneCallback { mapError ->
            if(mapError == null){
                val distance: Double = (1000*10).toDouble()
                MapView.setPrimaryLanguage(LanguageCode.EN_US)
                binding.mapView.camera.lookAt(GeoCoordinates(lat,lng),distance)
            }
            else{
                Log.d("MapError", "loadMapScene: $mapError")
            }
        })
        binding.mapView.mapScene.addMapMarker(mapMarker)
    }

    private fun route(geoCoordinates: GeoCoordinates){
        try{
            routingEngine = RoutingEngine()
        }catch (e:InstantiationError){
            throw RuntimeException("Initialisation of routing engine failed $e")
        }

        val start = Waypoint(geoCoordinates)
        val end = Waypoint(GeoCoordinates(lat,lng))

        val waypoints:List<Waypoint> = ArrayList(Arrays.asList(start,end))

//        routingEngine.calculateRoute(
//            waypoints,
//            CarOptions(),
//            CalculateRouteCallback(){
//
//            }
//        )
    }

    fun getLocation(){
        val fusedLocationProviderClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                val userLat = it.latitude
                val userLng = it.longitude

                route(GeoCoordinates(userLat,userLng))

            }
        }
    }
}