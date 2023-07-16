package com.example.lab1_kotlinapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class AddressList : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var shippingAddressRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        shippingAddressRef = database.reference.child("ShippingAddress")
        setContentView(R.layout.addresslist)

        // Retrieve data from Firebase and set it in TextViews
        val addressLine1TextView: TextView = findViewById(R.id.addressLine1)
        val addressLine2TextView: TextView = findViewById(R.id.addressLine2)
        val cityTextView: TextView = findViewById(R.id.city)
        val stateTextView: TextView = findViewById(R.id.state)
        val zipCodeTextView: TextView = findViewById(R.id.zipCode)


        val payButton = findViewById<Button>(R.id.payButton)

        payButton.setOnClickListener {
            Toast.makeText(this,"payment successfully completed", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Thankyou::class.java)
            startActivity(intent)
        }

        shippingAddressRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {



                    val address = dataSnapshot.getValue(Address::class.java)
                    println(address)

                    address?.let {
                        addressLine1TextView.text = it.addressLine1
                        addressLine2TextView.text = it.addressLine2
                        cityTextView.text = it.city
                        stateTextView.text = it.state
                        zipCodeTextView.text = it.zipCode
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }
}
