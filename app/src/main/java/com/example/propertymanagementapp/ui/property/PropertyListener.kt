package com.example.propertymanagementapp.ui.property

import androidx.lifecycle.LiveData
import com.example.propertymanagementapp.data.models.MyProperty
import com.example.propertymanagementapp.data.models.PropertyResponse

interface PropertyListener {

    fun onStarted()
    fun onSuccess(response: LiveData<MyProperty>)
    fun failure(message:String)
}