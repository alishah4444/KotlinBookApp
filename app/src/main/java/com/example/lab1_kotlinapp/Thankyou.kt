package com.example.lab1_kotlinapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Thankyou:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.thankyou)

        val payButton = findViewById<Button>(R.id.continueShoppingButton)

        payButton.setOnClickListener {
            Toast.makeText(this,"payment successfully completed", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}