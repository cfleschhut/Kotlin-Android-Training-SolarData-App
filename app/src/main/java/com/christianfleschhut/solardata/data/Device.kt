package com.christianfleschhut.solardata.data

import com.squareup.moshi.Json

data class Device(
    val id: Int?,
    val name: String,
    val output: String? = null,
    @Json(name = "img_src") val image: String? = null,
    val description: String? = null
)
