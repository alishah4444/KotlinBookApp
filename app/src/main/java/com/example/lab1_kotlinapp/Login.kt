package com.example.lab1_kotlinapp;


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import android.content.Context
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class Login : AppCompatActivity()  {




    private lateinit var inputUsername: TextInputEditText
    private lateinit var inputPassword: TextInputEditText
    private lateinit var switchRememberMe: Switch
    private lateinit var btnLogin: Button
    private lateinit var txtForgotPassword: TextView
    private lateinit var txtCreateNewAccount: TextView
    private lateinit var btnCreateNewAccount: Button
    private lateinit var btnGoBackHome: Button
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var usersRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        inputUsername = findViewById(R.id.txtHintUsername)
        inputPassword = findViewById(R.id.txtHintPassworde)
        switchRememberMe = findViewById(R.id.swtRmb)
        btnLogin = findViewById(R.id.btnLogin)
        btnCreateNewAccount = findViewById(R.id.btnCreateNewAccount)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        txtCreateNewAccount = findViewById(R.id.textView6)
        btnGoBackHome = findViewById(R.id.goBackHome)

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();


        val database = FirebaseDatabase.getInstance()
        usersRef = database.reference.child("users")

        btnLogin.setOnClickListener {
            val username = inputUsername.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            loginUser(username, password)
        }


        btnCreateNewAccount.setOnClickListener{
            Toast.makeText(this,
                "Register the Account", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Register::class.java)
            intent.putExtra("key", "Kotlin")
            startActivity(intent)

        }


        btnGoBackHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

    private fun loginUser(username: String, password: String) {
        val query = usersRef.orderByChild("email").equalTo(username)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val userSnapshot = snapshot.children.first()
                    val user = userSnapshot.getValue(Person::class.java)

                    if (user?.password == password) {

                       println("matches pass")
                        try {
                            firebaseAuth.createUserWithEmailAndPassword(username, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        // Authentication successful
                                        val user = firebaseAuth.currentUser
                                        saveUserData(user?.uid, user?.email,)
                                        setLoginStatus(true)

                                    } else {
                                        // Authentication failed
                                        val exception = task.exception
                                        if (exception is FirebaseAuthUserCollisionException) {

                                        } else {
                                            println("auth fail")

                                        }
                                    }
                                }
                        } catch (e: Exception) {
                            println("catch error")

                        }


                    } else {
                        println("no matches pass")

                    }
                } else {
                    println("server error")

                }
            }

            override fun onCancelled(error: DatabaseError) {
                println(error)

            }
        })
    }

    private fun saveUserData(userId: String?, userEmail: String?) {
        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userId", userId)
        editor.putString("userEmail", userEmail)

        editor.apply()
    }


    private fun setLoginStatus(isLoggedIn: Boolean) {
        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        Toast.makeText(this,
            "Welcome Back", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
        editor.apply()
    }
}
