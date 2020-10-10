package com.example.propertymanagementapp.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagementapp.data.models.LoginResponse
import com.example.propertymanagementapp.data.models.User
import com.example.propertymanagementapp.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {

    fun login(email: String, password: String): LiveData<String> {
        var loginResponse = MutableLiveData<String>()
        var user = User(email = email, password = password)

        MyApi().login(user)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
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


}

