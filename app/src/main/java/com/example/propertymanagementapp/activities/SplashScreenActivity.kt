package com.example.propertymanagementapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.propertymanagementapp.R

class SplashScreenActivity : AppCompatActivity() {
    private val delayedTime:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        init()
    }

    private fun init() {
        var handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this, LoginOrRegisterActivity::class.java))
        }, delayedTime)
    }
}