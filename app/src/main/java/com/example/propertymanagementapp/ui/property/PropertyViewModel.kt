package com.example.propertymanagementapp.ui.property

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapp.data.repositories.PropertyRepository

class PropertyViewModel : ViewModel() {

    var image: String? = null
    var address: String? = null
    var city: String? = null
    var state: String? = null
    var propertyListener: PropertyListener? = null
    var getPropertyListener: GetPropertyListener? = null

    fun onAddPropertyClicked(view: View) {

        if (address.isNullOrEmpty() || city.isNullOrEmpty() || state.isNullOrEmpty() || image.isNullOrEmpty()) {
            propertyListener?.failure("All fields are required")
            return
        }

        var propertyResponse = PropertyRepository().addProperty(address!!, city!!, state!!, image!!)
        propertyListener?.onSuccess(propertyResponse)
    }

    fun addImage(imageURL: String) {

        image = imageURL
    }

    fun getPropertyClicked(view: View) {
        var propertyResponse = PropertyRepository().getProperty()
        getPropertyListener?.onSuccess(propertyResponse)
    }
//
//    fun getPropertyClicked(view:View){
//        var propertyResponse = PropertyRepository().getPropertyById(view.context)
//        getPropertyListener?.onSuccess(propertyResponse)
//    }
}