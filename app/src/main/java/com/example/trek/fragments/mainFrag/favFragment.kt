package com.example.trek.fragments.mainFrag

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trek.adapter.placesAdapter
import com.example.trek.databinding.FragmentFavBinding
import com.example.trek.model.PlacesItem
import com.example.trek.viewModel.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class favFragment:Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var places: ArrayList<PlacesItem> = arrayListOf()
    private lateinit var placeAdapter: placesAdapter
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var firebaseUser: FirebaseUser
//    private lateinit var placeAdapter:FirestoreRecyclerAdapter<PlacesItem,PlaceViewHolder>

    private lateinit var binding: FragmentFavBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(inflater, container, false)

        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        setFav()

        return binding.root
    }

    private fun setFav() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser!!
        val reference =
                FirebaseFirestore.getInstance().
                collection("Trek").
                document(firebaseUser.uid).
                collection("FavPlaces")
        reference.get().addOnSuccessListener {
            places.clear()
            for(snapshot in it.documents){
                val place = snapshot.toObject(PlacesItem::class.java)
                Log.d("Favourite", "setFav: ${place?.name}")
                places.add(place!!)
            }
//            Log.d("Favourite", "setFav: ${it.documents[1]}")
            placeAdapter = placesAdapter(requireContext(),places)
            binding.favRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.favRecyclerView.setAdapter(placeAdapter)
        }

    }
}