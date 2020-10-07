package com.example.propertymanagementapp.models

data class Tenant(
    val email: String,
    val landlordEmail: String,
    val name: String,
    val password: String,
    val type: String
)

data class Landlord(
    val email: String,
    val name: String,
    val password: String,
    val type: String
)