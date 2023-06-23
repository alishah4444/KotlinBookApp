package com.example.lab1_kotlinapp

import PersonAdapter
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab1_kotlinapp.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp

import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var personAdapter: PersonAdapter
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dbRef = FirebaseDatabase.getInstance().getReference("Book")

        println(dbRef)

        personAdapter = PersonAdapter(mutableListOf())
        binding.rView.layoutManager = LinearLayoutManager(this)
        binding.rView.adapter = personAdapter

        setSupportActionBar(binding.toolbar)

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