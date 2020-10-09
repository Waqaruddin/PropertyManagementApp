package com.example.propertymanagementapp.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {

    fun onStarted()
    fun onSuccess(response: LiveData<String>)
    fun failure(message:String)
}