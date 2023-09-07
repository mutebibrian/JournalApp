package com.mastercoding.journalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.mastercoding.journalapp.databinding.ActivityMainBinding
import com.mastercoding.journalapp.databinding.ActivitySignUpBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    // Firebase Auth
    private lateinit var auth: FirebaseAuth


    // Firebase Connection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.createAcctBTN.setOnClickListener(){
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.emailSignInButton.setOnClickListener(){
            LoginWithEmailPassword(
                binding.email.text.toString().trim(),
                binding.password.text.toString().trim()
            )
        }


        // Auth Ref
        auth = Firebase.auth




    }

    private fun LoginWithEmailPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    // Sign in Success

                    var journal : JournalUser = JournalUser.instance!!
                    journal.userId  = auth.currentUser?.uid
                    journal.username = auth.currentUser?.displayName


                    goToJournalList()

                }else{
                    Toast.makeText(
                        this,
                        "Authentication Failed",
                        Toast.LENGTH_LONG
                    ).show()
                }

        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

        if (currentUser != null){
            goToJournalList()
        }
    }

    private fun goToJournalList() {
        var intent  = Intent(this, JournalList::class.java)
        startActivity(intent)
    }


}
