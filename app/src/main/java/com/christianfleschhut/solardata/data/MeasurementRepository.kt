package com.christianfleschhut.solardata.data

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MeasurementRepository {

    private fun getTextFromAsset(context: Context, fileName: String): String {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
    }

    fun getMeasurements(context: Context, fileName: String): List<Measurement>? {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(
            List::class.java, Measurement::class.java
        )
        val adapter: JsonAdapter<List<Measurement>> = moshi.adapter(listType)
        return adapter.fromJson(getTextFromAsset(context, fileName))
    }

}