package com.example.trek.fragments.mainFrag

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trek.activities.AuthenticationActivity
import com.example.trek.databinding.FragmentProfileBinding
import com.example.trek.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source

class profileFragment : Fragment() {

    private lateinit var firebaseUser: FirebaseUser
    private lateinit var documentReference: DocumentReference
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)

        firebaseUser = FirebaseAuth.getInstance().currentUser!!

        binding.logoutBtn.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireContext(), AuthenticationActivity::class.java)
            startActivity(intent)
            activity?.finish()
        })
        setDetails()
        return binding.root
    }

    private fun setDetails(){
        documentReference = FirebaseFirestore.getInstance().
                                collection("Trek").
                                document(firebaseUser.uid).
                                collection("userData").
                                document("data")
        val source = Source.CACHE
        documentReference.get(source).addOnSuccessListener {
            val user = it.toObject(User::class.java)
            binding.profileUserName.text = user?.name

        }

    }
}