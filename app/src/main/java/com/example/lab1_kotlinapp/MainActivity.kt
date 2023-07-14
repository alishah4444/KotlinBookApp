package com.example.lab1_kotlinapp

import PersonAdapter
import android.content.Context

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab1_kotlinapp.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp

import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var personAdapter: PersonAdapter
    private lateinit var dbRef: DatabaseReference
    private lateinit var  textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textView =findViewById(R.id.loginBtn);

        dbRef = FirebaseDatabase.getInstance().getReference("Book")

        println(dbRef)

        personAdapter = PersonAdapter(mutableListOf())
        binding.rView.layoutManager = LinearLayoutManager(this)
        binding.rView.adapter = personAdapter

        setSupportActionBar(binding.toolbar)

        val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        val  userId = sharedPreferences.getString("userId", null)
        val userEmail = sharedPreferences.getString("userEmail", null)





        // Check if the user data is available
        if (userId != null && userEmail != null  ) {
            // Use the user data as needed
            println("User ID: $userId")
            println("User Email: $userEmail")

            textView.text=userEmail;
            Toast.makeText(this,"Welcome back $userEmail",Toast.LENGTH_LONG).show()


        } 

        textView?.setOnClickListener{


            if (userId != null && userEmail != null  ) {


                val intent = Intent(this@MainActivity, Profile::class.java)
                startActivity(intent)

            } else{

                Toast.makeText(
                    this@MainActivity,
                    "Login", Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this@MainActivity, Login::class.java)
                startActivity(intent)
            }
        }

        fetchDataFromDatabase()

    }

    private fun fetchDataFromDatabase() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val personList = mutableListOf<Book>()

                for (snapshot in dataSnapshot.children) {
                    val person = snapshot.getValue(Book::class.java)
                    person?.let {
                        personList.add(it)
                    }
                }

                personAdapter.setPersonList(personList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error
            }
        })
    }


}




