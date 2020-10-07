package com.example.propertymanagementapp.models

data class RegisterResponse(
    val `data`: Data,
    val error: Boolean,
    val message: String
)

data class Data(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val name: String,
    val password: String,
    val type: String
)