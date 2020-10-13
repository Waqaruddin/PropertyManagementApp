package com.example.propertymanagementapp.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagementapp.data.models.MyProperty
import com.example.propertymanagementapp.data.models.PropertyResponse
import com.example.propertymanagementapp.data.network.MyApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PropertyRepository{



    fun addProperty(address:String):LiveData<String>{
        var propertyResponse = MutableLiveData<String>()
        var property = MyProperty(address = address)

        MyApi().addProperty(property)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object:DisposableSingleObserver<PropertyResponse>(){
                override fun onSuccess(t: PropertyResponse) {
                    propertyResponse.value = "Added Property"
                    Log.d("abc", t.toString() )
                }

                override fun onError(e: Throwable) {
                    Log.d("abc", e.message!!)

                }

            })

        return propertyResponse

    }
}