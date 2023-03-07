package com.christianfleschhut.solardata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.christianfleschhut.solardata.data.Device
import com.christianfleschhut.solardata.data.DeviceRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val deviceRepository: DeviceRepository = DeviceRepository()

    val devices: LiveData<List<Device>> = liveData {
        val data = deviceRepository.getDevices()
        emit(data)
    }

}