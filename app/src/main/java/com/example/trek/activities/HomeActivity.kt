package com.example.trek.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.trek.R
import com.example.trek.databinding.ActivityHomeBinding
import com.example.trek.fragments.mainFrag.HomeFragment
import com.example.trek.fragments.mainFrag.favFragment
import com.example.trek.fragments.mainFrag.profileFragment
import com.example.trek.fragments.mainFrag.searchFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {
    companion object{
        val cacheSize = (10*1024*1024).toLong()
    }

    private lateinit var firebaseUser: FirebaseUser
    private lateinit var data:Intent
    private lateinit var firestore: FirebaseFirestore
    private lateinit var documentReference: DocumentReference


    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        data = intent

        supportFragmentManager.beginTransaction()
            .replace(R.id.homeFrameLayout,HomeFragment()).commit()
        bottomBar()

        Log.d("UserName3", "onCodeSent: ${data.getStringExtra("name")}")

    }

    private fun bottomBar(){
        binding.bottomBar.setItemSelected(R.id.home,true)
        binding.bottomBar.setOnItemSelectedListener {
            when(it){
                R.id.home -> supportFragmentManager.beginTransaction()
                    .replace(R.id.homeFrameLayout,HomeFragment()).commit()

                R.id.find -> supportFragmentManager.beginTransaction()
                    .replace(R.id.homeFrameLayout,searchFragment()).commit()

                R.id.fav -> supportFragmentManager.beginTransaction()
                    .replace(R.id.homeFrameLayout,favFragment()).commit()

                R.id.profile -> supportFragmentManager.beginTransaction()
                    .replace(R.id.homeFrameLayout,profileFragment()).commit()
            }
        }
    }


//    private fun user(){
//        val user = FirebaseAuth.getInstance().currentUser
//        val db = Firebase.firestore
//
//
////        val documentReference: DocumentReference = firestore.
////        collection("Trek").
////        document(user.uid).
////        collection("UserData").
////        document();
//        val userData = hashMapOf(
//            "name" to data.getStringExtra("name")
//        )
//
//        if (user != null) {
//            db.collection("Users").
//            document(user.uid).
//            set(userData)
//        }
////        documentReference.set(map)
//    }

}