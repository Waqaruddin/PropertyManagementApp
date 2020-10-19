package com.example.propertymanagementapp.data.models

data class AddPropertyResponse(
    val count: Int,
    val data: List<AddProperty>,
    val error: Boolean
)

data class AddProperty(
    val __v: Int,
    val _id: String,
    val address: String,
    val city: String,
    val country: String,
    val image: String,
    val latitude: String,
    val longitude: String,
    val mortageInfo: Boolean,
    val propertyStatus: Boolean,
    val purchasePrice: String,
    val state: String,
    val userId: String,
    val userType: String
)
