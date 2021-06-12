package com.example.trek.activities

import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.trek.R
import com.example.trek.databinding.ActivityTranslateBinding
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.languageid.LanguageIdentifier

class TranslateActivity : AppCompatActivity(),View.OnClickListener{

    private lateinit var identifier: LanguageIdentifier
    private lateinit var clipboardManager: ClipboardManager
    private lateinit var binding: ActivityTranslateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        binding.translateBackBtn.setOnClickListener(this)
        binding.textClearBtn.setOnClickListener(this)
        binding.copyTextPrimary.setOnClickListener(this)
        binding.copyTextSecondary.setOnClickListener(this)
        binding.primaryLanguage.setOnClickListener(this)
        binding.secondaryLanguage.setOnClickListener(this)


    }

    fun identifyLanguage(){
        identifier = LanguageIdentification.getClient()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.translateBackBtn -> onBackPressed()

            R.id.textClearBtn -> {
                binding.textPrimary.setText("")
                identifier.close()
            }

            R.id.copyTextPrimary -> {
                val clip = ClipData.newPlainText("translateText",binding.textPrimary.text.toString())
                clipboardManager.setPrimaryClip(clip)
                Toast.makeText(applicationContext, "copied", Toast.LENGTH_SHORT).show()
            }

            R.id.copyTextSecondary -> {
                val clip = ClipData.newPlainText("translateText",binding.textSecondary.text.toString())
                clipboardManager.setPrimaryClip(clip)
                Toast.makeText(applicationContext, "copied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}