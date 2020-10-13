package com.example.propertymanagementapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.data.repositories.UserRepository

class AuthViewModel : ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    var name:String? = null
    var type:String? = null
    var landlordEmail:String? = null

    fun onLoginButtonClicked(view: View) {

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.failure("Email and password are required")
            return
        }

        var loginResponse = UserRepository().login(view.context,email!!, password!!)
        authListener?.onSuccess(loginResponse)



    }

    fun onRegisterLandlordClicked(view:View){
        if(email.isNullOrEmpty() || password.isNullOrEmpty() || name.isNullOrEmpty()){
            authListener?.failure("All fields are required")
        }
        var registerResponse = UserRepository().registerLandlord(name!!, email!!, password!! , "landlord")
        authListener?.onSuccess(registerResponse)
    }


    fun onRegisterTenantClicked(view:View){
        if(email.isNullOrEmpty() || password.isNullOrEmpty() || name.isNullOrEmpty() || landlordEmail.isNullOrEmpty()){
            authListener?.failure("All fields are required")
        }
        var registerResponse = UserRepository().registerTenant(name!!, email!!, password!! , landlordEmail!!, "tenant")
        authListener?.onSuccess(registerResponse)
    }





}