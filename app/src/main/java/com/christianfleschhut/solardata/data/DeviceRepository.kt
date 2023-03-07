package com.christianfleschhut.solardata.data

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class DeviceRepository {

    fun getTextFromResource(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use { it.readText() }
    }

    fun getTextFromAsset(context: Context, fileName: String): String {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
    }

    fun getDevices(context: Context, fileName: String): List<Device>? {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(
            List::class.java, Device::class.java
        )
        val adapter: JsonAdapter<List<Device>> = moshi.adapter(listType)
        return adapter.fromJson(getTextFromAsset(context, fileName))
    }

}
