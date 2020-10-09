package com.example.propertymanagementapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagementapp.data.models.LoginResponse
import com.example.propertymanagementapp.data.models.User
import com.example.propertymanagementapp.data.network.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {
    fun login(email:String, password:String):LiveData<String>{
        var loginResponse = MutableLiveData<String>()
        var loginUser = User(email, password)

        MyApi().login(loginUser)
            .enqueue(object:Callback<LoginResponse>{
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if(response.isSuccessful){
                        loginResponse.value = "Login Successful"
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginResponse.value = t.message
                }

            })
        return loginResponse
    }
}

