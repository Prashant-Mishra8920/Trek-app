package com.example.trek.fragments.mainFrag

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trek.adapter.placesAdapter

import com.example.trek.databinding.FragmentSearchBinding
import com.example.trek.model.PlacesItem
import com.example.trek.repository.Repository
import com.example.trek.viewModel.HomeViewModel
import com.example.trek.viewModel.HomeViewModelFactory
import retrofit2.converter.gson.GsonConverterFactory

class searchFragment : Fragment() {

    private val api_key = "AHbk8cNIHZ15xE3hIVDn24yDwshIn5aK4T3Qgk6e"
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel:HomeViewModel
    private var placesWithQuery:ArrayList<PlacesItem> = arrayListOf()
    private lateinit var placeAdapter: placesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {1
        binding = FragmentSearchBinding.inflate(inflater,container,false)

        binding.searchBar.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                val query:String = binding.searchBar.text.toString()
//                request(query)
                binding.seachFragText.visibility = View.GONE
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.searchBtn.setOnClickListener(View.OnClickListener {
            var query:String = binding.searchBar.text.toString()
            request(query)
        })
        return binding.root
    }

    fun request(query:String){
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
        viewModel.getPlaceWithQuery(query,"0.05:")
        viewModel.responseWithQuery.observe(viewLifecycleOwner, Observer {
            placesWithQuery.clear()
            for(place in it.data?.places!!){
                Log.d("Response", "onCreateView: ${place?.name}")
                Log.d("Response", "onCreateView: ${place?.perex}\n")
                placesWithQuery.add(place!!)
            }
            placeAdapter = placesAdapter(requireContext(),placesWithQuery)
            binding.searchRecyclerView.adapter = placeAdapter
            binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        })
    }
}