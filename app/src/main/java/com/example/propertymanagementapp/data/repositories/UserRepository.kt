package com.example.propertymanagementapp.data.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagementapp.data.models.*
import com.example.propertymanagementapp.data.network.MyApi
import com.example.propertymanagementapp.helpers.SessionManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {

    lateinit var sessionManager: SessionManager

    fun login( mContext:Context,  email: String, password: String): LiveData<String> {
        var loginResponse = MutableLiveData<String>()
        var user = User(email = email, password = password)


        MyApi().login(user)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        sessionManager = SessionManager(mContext)
                        sessionManager.saveUserLogin(response.body()?.token!!)
                        loginResponse.value = "Login Successful"
                        Log.d("abc", response.body()?.user?.name.toString())
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginResponse.value = t.message
                }

            })
        return loginResponse
    }

    fun registerTenant(name:String, email:String, password:String, landlordEmail:String, type:String):LiveData<String>{
        var registerResponse = MutableLiveData<String>()
        var registerTenant = Tenant(name = name, email = email, password = password, landlordEmail = landlordEmail, type = "tenant")

        MyApi().registerTenant(registerTenant)
            .enqueue(object:Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if(response.isSuccessful){
                        registerResponse.value = "Registered Successfully"
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    registerResponse.value = t.message
                }

            })
        return registerResponse

    }


    fun registerLandlord(name:String, email:String, password:String, type:String):LiveData<String>{
        var registerResponse = MutableLiveData<String>()
        var registerLandlord = Landlord(name = name, email = email, password = password, type =  "landlord")

        MyApi().registerLandlord(registerLandlord)
            .enqueue(object:Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if(response.isSuccessful){
                        registerResponse.value = "Registered Successfully"
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    registerResponse.value = t.message
                }

            })
        return registerResponse

    }


}

