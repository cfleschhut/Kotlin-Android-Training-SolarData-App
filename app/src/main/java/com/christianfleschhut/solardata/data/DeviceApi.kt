package com.christianfleschhut.solardata.data

import retrofit2.Response
import retrofit2.http.GET

interface DeviceApi {

    @GET("devices.json")
    suspend fun getDevices(): Response<List<Device>>

}