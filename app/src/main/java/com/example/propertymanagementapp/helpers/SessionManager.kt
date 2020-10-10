package com.example.propertymanagementapp.helpers

import android.content.Context

class SessionManager(private var mContext: Context) {

    private val FILE_NAME = "Registered_Users"
    private val KEY_TOKEN = "token"
    private val KEY_IS_LOGGED_IN = "isLoggedIn"

    var sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    fun saveUserLogin(token:String){
        editor.putString(KEY_TOKEN, token)
        editor.commit()
    }

    fun isLoggedIn():Boolean{
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout(){
        editor.clear()
        editor.commit()
    }

}