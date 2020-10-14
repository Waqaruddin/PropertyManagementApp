package com.example.propertymanagementapp.ui.property

import androidx.lifecycle.LiveData
import com.example.propertymanagementapp.data.models.MyProperty

interface GetPropertyListener {

    fun onStarted()
    fun onSuccess(response: LiveData<ArrayList<MyProperty>>)
    fun failure(message:String)
}