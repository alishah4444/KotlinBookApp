package com.example.lab1_kotlinapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Register : AppCompatActivity()  {

    private lateinit var register:Button
    private lateinit var goBack :Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        register= findViewById(R.id.btnCreateAcc)
        goBack= findViewById(R.id.goBackHome)
    }
}