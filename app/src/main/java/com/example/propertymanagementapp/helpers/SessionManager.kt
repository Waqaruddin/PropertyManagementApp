package com.example.propertymanagementapp.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.media.session.MediaSessionCompat.KEY_TOKEN
import com.example.propertymanagementapp.data.models.User

class SessionManager(private var mContext: Context) {

    private val FILE_NAME = "Registered_Users"
    private val KEY_TOKEN = "token"
    private val KEY_IS_LOGGED_IN = "isLoggedIn"
    private val KEY_ID = "_id"
    private val KEY_USER_NAME = "name"
    private val KEY_USER_EMAIL = "email"

    var sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    fun saveUserLogin(token:String){
        editor.putString(KEY_TOKEN, token)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.commit()
    }


    fun saveUserId(user: User){
        editor.putString(KEY_ID, user._id)
        editor.putString(KEY_USER_NAME , user.name)
        editor.putString( KEY_USER_EMAIL, user.email)
        editor.commit()
    }

    fun getUserId():String{
        var userId = sharedPreferences.getString(KEY_ID, null)
        return  userId.toString()
    }

    fun getUserName():String{
        var userName = sharedPreferences.getString(KEY_USER_NAME, null)
        return  userName.toString()

    }
    fun getUserEmail():String{
        var userEmail = sharedPreferences.getString(KEY_USER_EMAIL, null)
        return userEmail.toString()

    }

    fun isLoggedIn():Boolean{
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout(){
        editor.clear()
        editor.commit()
    }

    companion object{
        @SuppressLint("RestrictedApi")
        const val TOKEN = KEY_TOKEN
    }

}