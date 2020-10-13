package com.example.propertymanagementapp.ui.property

import androidx.lifecycle.LiveData

interface PropertyListener {

    fun onStarted()
    fun onSuccess(response: LiveData<String>)
    fun failure(message:String)
}