package com.example.propertymanagementapp.data.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagementapp.data.models.Image
import com.example.propertymanagementapp.data.models.ImageResponse
import com.example.propertymanagementapp.data.models.MyProperty
import com.example.propertymanagementapp.data.models.PropertyResponse
import com.example.propertymanagementapp.data.network.MyApi
import com.example.propertymanagementapp.helpers.SessionManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PropertyRepository {
    lateinit var sessionManager: SessionManager

    fun addProperty(address: String, imageURL:String): LiveData<String> {

        var propertyResponse = MutableLiveData<String>()
        var property = MyProperty(address = address, image = imageURL)

        MyApi().addProperty(property)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<PropertyResponse>() {
                override fun onSuccess(t: PropertyResponse) {
                    propertyResponse.value = "Added Property"
                    Log.d("abc", t.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("abc", e.message!!)

                }

            })

        return propertyResponse

    }

    fun getPropertyById(mContext: Context): LiveData<ArrayList<MyProperty>> {
        var propertyResponse = MutableLiveData<ArrayList<MyProperty>>()
        sessionManager = SessionManager(mContext)
        var userId = sessionManager.getUserId()

        MyApi().getPropertyById(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<PropertyResponse>() {
                override fun onSuccess(t: PropertyResponse) {
                    propertyResponse.value = t.data
                    Log.d("abc", t.data[0].address.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("abc", e.message!!)
                }

            })
        return propertyResponse
    }


fun getProperty(): LiveData<ArrayList<MyProperty>> {
    var propertyResponse = MutableLiveData<ArrayList<MyProperty>>()

    MyApi().getProperty()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableSingleObserver<PropertyResponse>() {
            override fun onSuccess(t: PropertyResponse) {
                propertyResponse.value = t.data
                Log.d("abc", t.data[0].address.toString())

            }

            override fun onError(e: Throwable) {
                Log.d("abc", e.message!!)

            }

        })
    return propertyResponse
}


//    fun uploadImage(path: String) {
//        var file = File(path)
//        var requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file)
//        var body = MultipartBody.Part.createFormData("image", file.name, requestFile)
//        MyApi().uploadImage(body)
//            .enqueue(object : Callback<ImageResponse> {
//                override fun onResponse(
//                    call: Call<ImageResponse>?,
//                    response: Response<ImageResponse>?
//                ) {
//                    if (response!!.isSuccessful) {
//                        Log.d("abc", response.body().data.location.toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ImageResponse>?, t: Throwable?) {
//
//                }
//
//            })
//    }
}
