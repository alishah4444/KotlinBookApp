package com.example.lab1_kotlinapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Cart : AppCompatActivity() {

    private lateinit var CartRView: RecyclerView
    private lateinit var CheckOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart)

        CheckOut = findViewById(R.id.btnCheckOut)

        CartRView = findViewById(R.id.cartRView)
        CartRView.layoutManager = LinearLayoutManager(this)



    }
}