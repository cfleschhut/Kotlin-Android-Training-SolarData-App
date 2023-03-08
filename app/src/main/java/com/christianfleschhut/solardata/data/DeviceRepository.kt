package com.christianfleschhut.solardata.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_ENDPOINT_URL = "http://192.168.1.4:3000/"

class DeviceRepository {

    private val retrofit: Retrofit by lazy {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val deviceApi: DeviceApi by lazy {
        retrofit.create(DeviceApi::class.java)
    }

    suspend fun getDevices(): List<Device> {
//        delay(2000)
        val response = deviceApi.getDevices()

        return if (response.isSuccessful)
            response.body() ?: emptyList()
        else
            emptyList()
    }

}
