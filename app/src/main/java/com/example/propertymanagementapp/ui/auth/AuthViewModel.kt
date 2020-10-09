package com.example.propertymanagementapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.data.repositories.UserRepository

class AuthViewModel: ViewModel() {
    var email:String? = null
    var password:String? = null
    var authListener:AuthListener? = null

    fun onLoginButtonClicked(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.failure("Email and password are required")
        }
        val loginResponse = UserRepository().login(email!!, password!!)
        authListener?.onSuccess(loginResponse)
    }
}