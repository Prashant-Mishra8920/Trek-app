package com.example.trek.fragments.authfrag

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trek.activities.HomeActivity
import com.example.trek.R
import com.example.trek.databinding.LoginFragmentLayoutBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class loginFragment : Fragment(R.layout.login_fragment_layout){

    private lateinit var binding: LoginFragmentLayoutBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val RC_SIGN_IN = 120
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = LoginFragmentLayoutBinding.inflate(inflater,container,false)
            binding.apply {
                signSwitch.setOnClickListener(View.OnClickListener {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.auth_layout,signFragment())
                        .commit()
                    })

                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
                googleSignInClient = GoogleSignIn.getClient(requireContext(),gso)


                googleSignIn.setOnClickListener(View.OnClickListener {
                    signIn()
                })

        }
        return binding.root
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if(task.isSuccessful){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("tag", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("tag", "Google sign in failed", e)
                }
            }
            else{
                Log.d("tag", "onActivityResult: "+exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d("tag", "signInWithCredential:success")
                    startActivity(Intent(requireContext(), HomeActivity::class.java))
                    activity?.finish()
                } else {
                    Log.w("tag", "signInWithCredential:failure", task.exception)
                }
            }
    }

}