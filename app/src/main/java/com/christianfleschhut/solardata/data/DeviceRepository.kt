package com.christianfleschhut.solardata.data

import android.content.Context

class DeviceRepository {

    fun getTextFromResource(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use {
                it.readText()
            }
    }

}
