package com.example.trek.fragments.authfrag

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.trek.activities.HomeActivity
import com.example.trek.databinding.OtpFragmentLayoutBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class otpFragment: Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var binding: OtpFragmentLayoutBinding
    private lateinit var name: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OtpFragmentLayoutBinding.inflate(inflater,container,false)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()

        name = arguments?.getString("name").toString()
        var mobile:String? = arguments?.getString("mobile")
        var verifyId: String? = arguments?.getString("verifyId")

        Log.d("UserName2", "onCodeSent: $name")
        binding.verifyOtpBtn.setOnClickListener(View.OnClickListener {
            val otp = binding.otpText.text.toString()

            if(!otp.isEmpty()){
                val phoneAuthCredential:PhoneAuthCredential = PhoneAuthProvider.getCredential(verifyId.toString(),otp)
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(
                    OnCompleteListener { task ->
                        if(task.isSuccessful){
                            user()
                            val intent = Intent(requireContext(), HomeActivity::class.java)
                            intent.putExtra("name",name)
                            startActivity(intent)
                            activity?.finish()
                        }
                        else{
                            Toast.makeText(requireContext(), "Invalid code", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        })


        return binding.root
    }

    private fun user(){
        val user = FirebaseAuth.getInstance().currentUser
        val db = Firebase.firestore

        val userData = hashMapOf(
            "name" to name
        )

        if (user != null) {
            db.collection("Trek").
                document(user.uid).
                collection("userData").
                document("data").set(userData)
        }
    }
}