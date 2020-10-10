package com.example.propertymanagementapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.data.network.MyApi
import com.example.propertymanagementapp.data.models.LoginResponse
import com.example.propertymanagementapp.data.models.User
import com.example.propertymanagementapp.databinding.ActivityLoginBinding
import com.example.propertymanagementapp.helpers.SessionManager
import com.example.propertymanagementapp.ui.home.HomeScreenActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), AuthListener {
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this
        sessionManager = SessionManager(this)

        init()
    }

    private fun init() {
//        button_login.setOnClickListener {
//            var api = MyApi()
//            var email = edit_text_email.text.toString()
//            var password = edit_text_password.text.toString()
//
//            var user = User(email = email, password =  password)
//            api.login(user)
//                .enqueue(object:Callback<LoginResponse>{
//                    override fun onResponse(
//                        call: Call<LoginResponse>,
//                        response: Response<LoginResponse>
//                    ) {
//                        Log.d("abc", response.message())
//                        if(response.message() == "Not Found"){
//                            Toast.makeText(applicationContext, "Email or password is incorrect", Toast.LENGTH_SHORT).show()
//                        }else{
//                            Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
//                            startActivity(Intent(applicationContext, HomeScreenActivity::class.java))
//                        }
//                    }
//
//                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//
//                    }
//
//                })

//        }

        text_view_click_here.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onStarted() {

        Toast.makeText(this, "Login starting", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(response: LiveData<String>) {
        response.observe(this, Observer {

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeScreenActivity::class.java))
        })
    }

    override fun failure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}