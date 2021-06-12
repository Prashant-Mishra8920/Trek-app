package com.example.trek.fragments.authfrag

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trek.activities.HomeActivity
import com.example.trek.R
import com.example.trek.databinding.LoginsignFragmentLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class loginSignFragment : Fragment(R.layout.loginsign_fragment_layout){

    private lateinit var binding: LoginsignFragmentLayoutBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginsignFragmentLayoutBinding.inflate(inflater,container,false)

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        if(user != null){
            val homeIntent = Intent(context, HomeActivity::class.java)
            startActivity(homeIntent)
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        login fragment transaction .................................
        binding.loginBtn.setOnClickListener(View.OnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.auth_layout,loginFragment())
                .commit()
        })

//        signup fragment transaction ..................................
        binding.signBtn.setOnClickListener(View.OnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.auth_layout,signFragment())
                .commit()
        })
    }
}