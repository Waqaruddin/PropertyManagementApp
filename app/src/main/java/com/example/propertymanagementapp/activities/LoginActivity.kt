package com.example.propertymanagementapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.api.MyApi
import com.example.propertymanagementapp.models.LoginResponse
import com.example.propertymanagementapp.models.User
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        button_login.setOnClickListener {
            var api = MyApi()
            var email = edit_text_email.text.toString()
            var password = edit_text_password.text.toString()

            var user = User(email = email, password =  password)
            api.login(user)
                .enqueue(object:Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        Log.d("abc", response.message())
                        if(response.message() == "Not Found"){
                            Toast.makeText(applicationContext, "Email or password is incorrect", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, HomeScreenActivity::class.java))
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    }

                })

        }

        text_view_click_here.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}