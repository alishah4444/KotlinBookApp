package com.example.lab1_kotlinapp;


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity()  {

    private lateinit var Registration:Button
    private lateinit var GoBack:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        Registration=findViewById(R.id.btnCreateNewAccount);
        GoBack = findViewById(R.id.goBackHome);

        Registration.setOnClickListener{
            Toast.makeText(this,
                "Register the Account", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Register::class.java)
            intent.putExtra("key", "Kotlin")
            startActivity(intent)

        }


        GoBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}