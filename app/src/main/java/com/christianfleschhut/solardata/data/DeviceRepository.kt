package com.christianfleschhut.solardata.data

import android.app.Application
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

const val BASE_ENDPOINT_URL = "https://solardata-app-backend.surge.sh/"

class DeviceRepository(val app: Application) {

    private val moshi: Moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val deviceApi: DeviceApi by lazy {
        retrofit.create(DeviceApi::class.java)
    }

    suspend fun getDevices(): List<Device> {
        val devicesFromCache = readDataFromFile()
        if (devicesFromCache.isNotEmpty()) {
            Log.i("DeviceRepository", "Data loaded from cache")
            return devicesFromCache
        }

        val response = deviceApi.getDevices()
        return if (response.isSuccessful) {
            Log.i("DeviceRepository", "Data loaded from webservice")
            val devices = response.body() ?: emptyList()
            storeDataInFile(devices)

            devices
        } else
            emptyList()
    }

    private fun storeDataInFile(devices: List<Device>) {
        val listType = Types.newParameterizedType(List::class.java, Device::class.java)
        val fileContents = moshi.adapter<List<Device>>(listType).toJson(devices)

        val file = File(app.cacheDir, "devices.json")
        file.writeText(fileContents)
    }

    private fun readDataFromFile(): List<Device> {
        val file = File(app.cacheDir, "devices.json")
        val json = if (file.exists()) file.readText() else null

        return if (json == null)
            emptyList()
        else {
            val listType = Types.newParameterizedType(List::class.java, Device::class.java)
            moshi.adapter<List<Device>>(listType).fromJson(json) ?: emptyList()
        }
    }

}
