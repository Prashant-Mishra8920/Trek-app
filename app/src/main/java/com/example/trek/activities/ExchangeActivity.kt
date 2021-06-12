package com.example.trek.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.trek.R
import com.example.trek.databinding.ActivityExchangeBinding
import com.example.trek.fragments.mainFrag.HomeFragment
import com.example.trek.model.Exchange
import com.example.trek.repository.Repository
import com.example.trek.room.ExchangeDatabase
import com.example.trek.viewModel.HomeViewModel
import com.example.trek.viewModel.HomeViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.util.*


class ExchangeActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener{
    private lateinit var binding: ActivityExchangeBinding
    private lateinit var viewModel: HomeViewModel
    private var primaryAmount:Double = 0.0
    private var secondaryAmount:Double = 0.0
    private lateinit var primaryCurrency:String
    private lateinit var secondaryCurrency:String
    private lateinit var dataBase:ExchangeDatabase
    private var isAvailable = 0
    private lateinit var shareprefs:SharedPreferences
    private var selectedCurrency:String = ""
    private lateinit var listOfCurrency:List<Exchange>

    private var value:Int = 0

    private lateinit var user:FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        shareprefs = getSharedPreferences("com.example.trek.exchange",Context.MODE_PRIVATE)

        val array:ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this,R.array.currencySpinner,android.R.layout.simple_spinner_item)
        val stringPri = shareprefs.getString("primaryCurrency","")
        val stringSec = shareprefs.getString("secondaryCurrency","")
        if(stringPri != "" && stringSec != "") {
            binding.currencySpinnerPrimary.setSelection(array.getPosition(stringPri))
            binding.currencySpinnerSecondary.setSelection(array.getPosition(stringSec))
        }

        dataBase = ExchangeDatabase.getDatabase(this)
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)

        user = FirebaseAuth.getInstance().currentUser!!
        binding.exchangeBackBtn.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        binding.currencySpinnerPrimary.onItemSelectedListener = this
        binding.currencySpinnerSecondary.onItemSelectedListener = this

        binding.primaryAmount.setOnFocusChangeListener(View.OnFocusChangeListener(){ view: View, b: Boolean ->
                if(b){
                    selectedCurrency = "1"
                }
        })
        binding.secondaryAmount.setOnFocusChangeListener(View.OnFocusChangeListener(){ view: View, b: Boolean ->
                if(b){
                    selectedCurrency = "2"
                }
        })

        binding.calculateExchange.setOnClickListener(View.OnClickListener {
            if(binding.primaryAmount.text.toString() != ""){
                primaryAmount = (binding.primaryAmount.text.toString()).toDouble()
            }
            if(binding.secondaryAmount.text.toString() != ""){
                secondaryAmount = (binding.secondaryAmount.text.toString()).toDouble()
            }
            setData()
        })
        getData()
    }

    fun updateRates(currrency:String){
        viewModel.getExchangeRate(currrency)
        viewModel.responseExchangeRate.observe(this, Observer {
            viewModel.addExchange(dataBase,it)
            getData()
        })
    }

    fun getData(){
//        viewModel.readAllData(dataBase)
        viewModel.readAllData(dataBase).observe(this, Observer {
            listOfCurrency = it
        })
    }

    fun setData(){
        val arrayTop = arrayListOf<String>()
        val date = Date()
        val formater = SimpleDateFormat("EEE, dd MMM yyyy")
        val d = formater.format(date)

            arrayTop.clear()
            for(i in listOfCurrency) {
                arrayTop.add(i.baseCode)
                Log.d("list", "getData: ${i.baseCode}")
            }
            if(arrayTop.contains(primaryCurrency) && arrayTop.contains(secondaryCurrency)) {
                for (i in listOfCurrency) {
                    if (i.baseCode == primaryCurrency && selectedCurrency == "1") {
                        isAvailable = 1
                        val array: Array<String> = ((i.conversionRates.toString()).substring(
                            16,
                            i.conversionRates.toString().length - 2
                        )).split(",").toTypedArray()
                        for (data in array) {
                            if (data.contains(secondaryCurrency)) {
                                val va = ((data.substring(5).toDouble()) * primaryAmount)
                                binding.secondaryAmount.setText(String.format("%.3f", va))
                            }
                        }
                    }
                    if (selectedCurrency == "2" && i.baseCode == secondaryCurrency) {
                        isAvailable = 1
                        val array: Array<String> = ((i.conversionRates.toString()).substring(
                            16,
                            i.conversionRates.toString().length - 2
                        )).split(",").toTypedArray()
                        for (data in array) {
                            if (data.contains(primaryCurrency)) {
                                val va = ((data.substring(5).toDouble()) * secondaryAmount)
                                binding.primaryAmount.setText(String.format("%.3f", va))
                            }
                        }
                    }
                }
            }
            if(!arrayTop.contains(primaryCurrency)){
                Toast.makeText(this, "${primaryCurrency} isn't available offline", Toast.LENGTH_SHORT).show()
            }
            if(!arrayTop.contains(secondaryCurrency)){
                Toast.makeText(this, "${secondaryCurrency} isn't available offline", Toast.LENGTH_SHORT).show()

            }
    }


    fun delete(){
        viewModel.deleteAll(dataBase)
    }



    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val editor:SharedPreferences.Editor = shareprefs.edit()
        if(parent?.id == R.id.currencySpinnerPrimary){
            val text = parent.getItemAtPosition(position).toString()
            primaryCurrency = text
            editor.putString("primaryCurrency",text)
            if(HomeFragment.isNetworkAvailable(applicationContext)){
                updateRates(primaryCurrency)
            }
        }
        else if(parent?.id == R.id.currencySpinnerSecondary){
            val text = parent.getItemAtPosition(position).toString()
            secondaryCurrency = text
            editor.putString("secondaryCurrency",text)
            if(HomeFragment.isNetworkAvailable(applicationContext)){
                updateRates(secondaryCurrency)
            }
        }
        editor.apply()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}