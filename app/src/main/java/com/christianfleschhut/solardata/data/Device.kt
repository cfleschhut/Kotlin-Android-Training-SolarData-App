package com.christianfleschhut.solardata.data

import com.squareup.moshi.Json

data class Device(
    val name: String,
    val output: String,
    @Json(name = "img_src") val image: String,
    val description: String
)
