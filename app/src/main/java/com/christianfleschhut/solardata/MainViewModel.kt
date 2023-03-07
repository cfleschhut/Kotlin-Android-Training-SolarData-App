package com.christianfleschhut.solardata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.christianfleschhut.solardata.data.Device
import com.christianfleschhut.solardata.data.DeviceRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    val devices: MutableLiveData<List<Device>> = MutableLiveData()

    private val deviceRepository: DeviceRepository = DeviceRepository()

    init {
        val data = deviceRepository.getDevices(app, "devices.json")
        data?.let {
            devices.value = it
        }
    }

}