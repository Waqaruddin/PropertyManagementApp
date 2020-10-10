package com.example.propertymanagementapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.helpers.SessionManager

class SplashScreenActivity : AppCompatActivity() {
    private val delayedTime:Long = 3000
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        init()
    }

    private fun init() {
        var handler = Handler()
        handler.postDelayed({
            checkLogin()
        }, delayedTime)
    }

    private fun checkLogin() {
        sessionManager = SessionManager(this)

        if(sessionManager.isLoggedIn()){
            startActivity(Intent(this, HomeScreenActivity::class.java))
        }else{
            startActivity(Intent(this, LoginOrRegisterActivity::class.java))
        }
    }
}