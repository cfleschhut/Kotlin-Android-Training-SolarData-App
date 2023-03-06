package com.christianfleschhut.solardata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.christianfleschhut.solardata.data.DeviceRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val deviceRepository: DeviceRepository = DeviceRepository()

    init {
        val data = deviceRepository.getTextFromResource(app, R.raw.devices)
        println(data)
    }

}