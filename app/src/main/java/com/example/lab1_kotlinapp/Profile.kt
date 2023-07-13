package com.example.lab1_kotlinapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Profile: AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var btnLogout:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        btnLogout = findViewById(R.id.btnLogout)

        firebaseAuth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("userEmail", "")

        val txtUsername: TextView = findViewById(R.id.txtUsername)
        txtUsername.text = username

        btnLogout.setOnClickListener {
            // Clear shared preference data
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            // Sign out from Firebase Authentication
            firebaseAuth.signOut()

            // Redirect to login screen
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    }