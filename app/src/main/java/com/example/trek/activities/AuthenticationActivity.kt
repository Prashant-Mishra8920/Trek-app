package com.example.trek.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trek.R
import com.example.trek.databinding.ActivityAuthBinding
import com.example.trek.fragments.authfrag.signFragment

class AuthenticationActivity : AppCompatActivity() {

        private lateinit var binding: ActivityAuthBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityAuthBinding.inflate(layoutInflater)
            setTheme(R.style.Theme_Trek)
            setContentView(binding.root)

            supportFragmentManager.beginTransaction().replace(R.id.auth_layout,signFragment()).commit()

    }
}