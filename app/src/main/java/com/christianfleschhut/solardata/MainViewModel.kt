package com.christianfleschhut.solardata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.christianfleschhut.solardata.data.Device
import com.christianfleschhut.solardata.data.DeviceRepository
import kotlinx.coroutines.delay

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val deviceRepository: DeviceRepository = DeviceRepository()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val devices: LiveData<List<Device>> = liveData {
        _isLoading.value = true

        delay(4000)
        val data = deviceRepository.getDevices()
        emit(data)

        _isLoading.value = false
    }
}
