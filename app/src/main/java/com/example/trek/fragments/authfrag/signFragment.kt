package com.example.trek.fragments.authfrag

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.trek.activities.HomeActivity
import com.example.trek.R
import com.example.trek.commuicator.communicator
import com.example.trek.databinding.SignupFragmetnLayoutBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class signFragment : Fragment(){
    private lateinit var binding: SignupFragmetnLayoutBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var mobileNumber: String
    private lateinit var userName:String

    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var communicator: communicator

    private val RC_SIGN_IN = 120
    private val TAG = "trek error"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignupFragmetnLayoutBinding.inflate(inflater,container,false)

        mAuth = FirebaseAuth.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        if(user != null){
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        googleSignInClient = GoogleSignIn.getClient(requireContext(),gso)
//
//        binding.googleSignIn.setOnClickListener(View.OnClickListener {
//            signIn()
//        })

        binding.signUpBtn.setOnClickListener(View.OnClickListener {
            userName = binding.username.text.toString()
            mobileNumber = binding.mobileNumber.text.toString()
            if(mobileNumber.isEmpty() && userName.isEmpty()){
                Toast.makeText(context,"enter number correctly",Toast.LENGTH_SHORT).show()
            }
            else{
                mobileVerification()
            }
        })

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                Log.d(TAG, "onVerificationCompleted:$credential")
//                signInWithPhoneAuthCredential(credential)
            }
            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d(TAG, "onCodeSent:$verificationId")
                storedVerificationId = verificationId
//                resendToken = token

                val otp = otpFragment()
                val args  = Bundle()
                Log.d("UserName1", "onCodeSent: $userName")
                args.putString("name",userName)
                args.putString("mobile", mobileNumber)
                args.putString("verifyId",storedVerificationId)
                otp.arguments = args
                parentFragmentManager.beginTransaction()
                    .replace(R.id.auth_layout,otp)
                    .commit()
            }
        }

        hasPermissions()

        return binding.root
    }

    fun hasPermissions():Boolean{
        val sentMsg = ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.RECEIVE_SMS)
        val fineLoc = ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION)
        val array:ArrayList<String> = ArrayList()

        if(sentMsg != PackageManager.PERMISSION_GRANTED){
            array.add(Manifest.permission.RECEIVE_SMS)
        }
        if(fineLoc != PackageManager.PERMISSION_GRANTED){
            array.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if(!array.isEmpty()){
            ActivityCompat.requestPermissions(requireActivity(),array.toTypedArray(),1)
            return false
        }

        return true
    }

    private fun mobileVerification(){
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(mobileNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
//
//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
//
//    }
////
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
//                    val intent = Intent(context,HomeActivity::class.java)
//                    startActivity(intent)
//                    activity?.finish()
//                } else {
//                    Log.w("tag", "signInWithCredential:failure", task.exception)
//                }
//            }
//    }
}