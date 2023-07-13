package com.example.lab1_kotlinapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity()  {




    private lateinit var txtFirstName: TextInputEditText
    private lateinit var txtLastName: TextInputEditText
    private lateinit var txtEmail: TextInputEditText
    private lateinit var txtPassword: TextInputEditText
    private lateinit var txtRepeatPassword: TextInputEditText
    private lateinit var btnCreateAccount: Button
    private lateinit var btnGoToLogin: Button

    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)



        txtFirstName = findViewById(R.id.txtHintFname)
        txtLastName = findViewById(R.id.txtHintLname)
        txtEmail = findViewById(R.id.txtHintUsername)
        txtPassword = findViewById(R.id.txtHintPassword)
        txtRepeatPassword = findViewById(R.id.txtHintRepeat)
        btnCreateAccount = findViewById(R.id.btnCreateAcc)
        btnGoToLogin = findViewById(R.id.goBackHome)



        database = FirebaseDatabase.getInstance()

        btnCreateAccount.setOnClickListener {
            val firstName = txtFirstName.text.toString().trim()
            val lastName = txtLastName.text.toString().trim()
            val email = txtEmail.text.toString().trim()
            val password = txtPassword.text.toString().trim()
            val repeatPassword = txtRepeatPassword.text.toString().trim()

            if (password == repeatPassword) {
                saveUserToDatabase(firstName, lastName, email, password)
            } else {
                // Passwords do not match, show an error message
                // For example: txtRepeatPassword.error = "Passwords do not match"
            }
        }

        btnGoToLogin.setOnClickListener {
            // Navigate to the login activity
        }




    }
    private fun saveUserToDatabase(firstName: String, lastName: String, email: String, password: String) {
        val usersRef = database.reference.child("users")
        val userRef = usersRef.push()
        val user = Person(firstName, lastName, email, password)
        userRef.setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,
                        "successfully registered", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this,
                        "Failed to save Data", Toast.LENGTH_LONG).show()

                }
            }
    }
}