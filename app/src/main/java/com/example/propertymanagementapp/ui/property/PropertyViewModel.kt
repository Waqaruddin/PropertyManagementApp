package com.example.propertymanagementapp.ui.property

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.data.repositories.PropertyRepository

class PropertyViewModel: ViewModel() {

    var address:String? = null
    var propertyListener:PropertyListener? = null
    var getPropertyListener:GetPropertyListener? = null

    fun onAddPropertyClicked(view: View){

        var propertyResponse = PropertyRepository().addProperty(address!!)
        propertyListener?.onSuccess(propertyResponse)

    }

    fun getPropertyClicked(view:View){
        var propertyResponse = PropertyRepository().getProperty()
        getPropertyListener?.onSuccess(propertyResponse)
    }
}