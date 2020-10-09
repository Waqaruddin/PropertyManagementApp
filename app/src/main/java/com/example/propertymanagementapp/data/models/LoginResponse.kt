package com.example.propertymanagementapp.data.models

data class LoginResponse(
    val token: String,
    val user: User
)

data class User(
    val _id: String? = null,
    val email: String? = null,
    val name: String? = null,
    val password: String? = null,
    val type: String? = null
)