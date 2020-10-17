package com.example.propertymanagementapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.ui.auth.activities.LoginActivity
import com.example.propertymanagementapp.ui.auth.activities.RegisterActivity
import kotlinx.android.synthetic.main.activity_login_or_register.*

class LoginOrRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_or_register)

        init()
    }

    private fun init() {
        button_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        button_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}