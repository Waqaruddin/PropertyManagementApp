package com.example.propertymanagementapp.app

class Endpoints {
    companion object {

        private const val URL_PROPERTY = "property"

        fun getProperty(userId: String): String {
            return Config.BASE_URL + URL_PROPERTY + "/" + userId
        }
    }
}