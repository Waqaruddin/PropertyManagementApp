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
        var user = User(email, password)

        MyApi().login(email, password)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {

                        loginResponse.value = "Login Successful"
                        Log.d("abc", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

            })
        return loginResponse
    }
}

