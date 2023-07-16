package com.example.lab1_kotlinapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ShippingAddress: AppCompatActivity() {

    private lateinit var addressLine1EditText: EditText
    private lateinit var addressLine2EditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var stateEditText: EditText
    private lateinit var zipCodeEditText: EditText
    private lateinit var saveAddressButton: Button

    private lateinit var cardName: EditText
    private lateinit var cardNumber: EditText
    private lateinit var cardDate: EditText

    private lateinit var shippingAddressTextView: LinearLayout


    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var shippingAddressRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout)

        // Initialize Firebase Realtime Database reference
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        shippingAddressRef = database.reference.child("ShippingAddress")

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        addressLine1EditText = findViewById(R.id.addressLine1EditText)
        addressLine2EditText = findViewById(R.id.addressLine2EditText)
        cityEditText = findViewById(R.id.cityEditText)
        stateEditText = findViewById(R.id.stateEditText)
        zipCodeEditText = findViewById(R.id.zipCodeEditText)
        saveAddressButton = findViewById(R.id.saveAddressButton)
        cardName=findViewById(R.id.Cardname)
        cardNumber =findViewById(R.id.AccountNumber)
        cardDate=findViewById(R.id.AccountExpdate)

        shippingAddressTextView = findViewById(R.id.shippingAddressFormLayout)

        saveAddressButton.setOnClickListener {
            val addressLine1 = addressLine1EditText.text.toString()
            val addressLine2 = addressLine2EditText.text.toString()
            val city = cityEditText.text.toString()
            val state = stateEditText.text.toString()
            val zipCode = zipCodeEditText.text.toString()
            val cardName =cardName.text.toString();
            val cardNumber =cardNumber.text.toString();
            val cardDate =cardDate.text.toString();


            val userId = sharedPreferences.getString("userId", null)
            val userAddress = sharedPreferences.getString("addressLine1", null)

            if (userId != null) {
                println(userAddress)
                println(userId)

                saveShippingAddress(addressLine1, addressLine2, city, state, zipCode, userId,cardName,cardNumber,cardDate)
            }
        }
    }

    private fun saveShippingAddress(addressLine1: String, addressLine2: String, city: String, state: String, zipCode: String, userId:String,
                                    cardName:String,cardNumber:String,cardDate:String) {
        val shippingAddress = Address(addressLine1, addressLine2, city, state, zipCode,userId,cardName,cardNumber,cardDate)
        shippingAddressRef.setValue(shippingAddress) .addOnSuccessListener {
            // Data inserted successfully
            saveAddressToSharedPreferences(shippingAddress)

            shippingAddressTextView.visibility = View.VISIBLE
            Toast.makeText(this,"Successfully Added the Address", Toast.LENGTH_LONG).show()

            val addressIntent = Intent(this, AddressList::class.java)
            startActivity(addressIntent)
            finish()
        }
            .addOnFailureListener {
                Toast.makeText(this,"fail to Add the Address", Toast.LENGTH_LONG).show()
            }
    }

    private fun saveAddressToSharedPreferences(address: Address) {
        val editor = sharedPreferences.edit()
        editor.putString("addressLine1", address.addressLine1)
        editor.putString("addressLine2", address.addressLine2)
        editor.putString("city", address.city)
        editor.putString("state", address.state)
        editor.putString("zipCode", address.zipCode)
        editor.apply()
    }
}
