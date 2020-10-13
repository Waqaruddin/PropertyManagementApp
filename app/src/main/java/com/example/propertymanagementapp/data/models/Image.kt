package com.example.propertymanagementapp.data.models

data class ImageResponse(
    val data: Image,
    val error: Boolean,
    val message: String
)

data class Image(

    val location: String? = null,
    val metadata: Metadata? = null,

)

data class Metadata(
    val fieldName: String
)