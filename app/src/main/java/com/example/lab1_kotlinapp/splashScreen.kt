package com.example.lab1_kotlinapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class splashScreen : AppCompatActivity()  {

    private lateinit var logoImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        logoImageView = findViewById(R.id.logoImageView)

        val fadeInAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation)
        logoImageView.startAnimation(fadeInAnimation)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Finish the current activity if needed
        }, 3000)
    }
}