package com.example.propertymanagementapp.ui.property

import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.data.repositories.PropertyRepository

class PropertyViewModel: ViewModel() {

    var address:String? = null
    var propertyListener:PropertyListener? = null

    fun onAddPropertyClicked(){

        var propertyResponse = PropertyRepository().addProperty(address!!)
        propertyListener?.onSuccess(propertyResponse)

    }
}