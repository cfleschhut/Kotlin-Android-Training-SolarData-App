package com.christianfleschhut.solardata

import android.app.Application
import android.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.christianfleschhut.solardata.data.Device
import com.christianfleschhut.solardata.data.DeviceRepository
import kotlinx.coroutines.delay

const val PREF_KEY_USER = "user_email"

class MainViewModel(val app: Application) : AndroidViewModel(app) {

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

    val userInfo: MutableLiveData<String?> = MutableLiveData(
        getStoredPreference(PREF_KEY_USER)
    )

    private fun getStoredPreference(key: String): String? {
        return PreferenceManager.getDefaultSharedPreferences(app)
            .getString(key, "")
    }

    private fun storePreference(key: String, value: String) {
        PreferenceManager.getDefaultSharedPreferences(app)
            .edit()
            .putString(key, value)
            .apply()
    }

    fun storeUserInfo(value: String) {
        userInfo.value = value
        storePreference(PREF_KEY_USER, value)
    }

    private fun resetPreference(key: String) {
        PreferenceManager.getDefaultSharedPreferences(app)
            .edit()
            .remove(key)
            .apply()
    }
    fun resetUserInfo() {
        userInfo.value = null
        resetPreference(PREF_KEY_USER)
    }
}
