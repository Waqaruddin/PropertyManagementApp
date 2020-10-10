package com.example.propertymanagementapp.helpers

import android.content.Context
import android.util.Log
import android.widget.Toast


fun Context.toastShort(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

fun Context.debug(message:String){
    Log.d("abc", message)
}