package com.example.trek.fragments.authfrag
//
//import android.content.Context
//import android.content.Intent
//import android.util.Log
//import android.view.View
//import com.example.trek.activities.HomeActivity
//import com.example.trek.R
//import com.example.trek.databinding.LoginFragmentLayoutBinding
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.gms.common.api.ApiException
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.GoogleAuthProvider
//
//class GoogleSignUp(context:Context){
//    private lateinit var mAuth : FirebaseAuth
//
//    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//        .requestIdToken(getString(R.string.default_web_client_id))
//        .requestEmail()
//        .build()
//    private var googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context,gso)
//
//
//
//    private fun signIn() {
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            val exception = task.exception
//            if(task.isSuccessful){
//                try {
//                    // Google Sign In was successful, authenticate with Firebase
//                    val account = task.getResult(ApiException::class.java)!!
//                    Log.d("tag", "firebaseAuthWithGoogle:" + account.id)
//                    firebaseAuthWithGoogle(account.idToken!!)
//                } catch (e: ApiException) {
//                    // Google Sign In failed, update UI appropriately
//                    Log.w("tag", "Google sign in failed", e)
//                }
//            }
//            else{
//                Log.d("tag", "onActivityResult: "+exception.toString())
//            }
//        }
//    }
//
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        mAuth.signInWithCredential(credential)
//            .addOnCompleteListener() { task ->
//                if (task.isSuccessful) {
//                    Log.d("tag", "signInWithCredential:success")
//                    startActivity(Intent(context, HomeActivity::class.java))
//                    activity?.finish()
//                } else {
//                    Log.w("tag", "signInWithCredential:failure", task.exception)
//                }
//            }
//    }
//
//}