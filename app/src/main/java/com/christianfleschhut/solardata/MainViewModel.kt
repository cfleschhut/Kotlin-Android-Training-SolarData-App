package com.christianfleschhut.solardata

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.*
import com.christianfleschhut.solardata.data.Device
import com.christianfleschhut.solardata.data.DeviceRepository

private const val PREF_KEY_USER = "user_email"
private const val PREF_KEY_DEVICE = "selected_device"
private const val TAG = "MainViewModel"

class MainViewModel(val app: Application) : AndroidViewModel(app) {

    private val deviceRepository: DeviceRepository = DeviceRepository(app)

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    val devices: LiveData<List<Device>> = liveData {
        _errorMessage.value = null
        _isLoading.value = true

        try {
//            delay(400)

            val fetchedDevices = deviceRepository.getDevices()
            Log.i(TAG, "Fetched devices: $fetchedDevices")

            emit(fetchedDevices)

        } catch (e: Exception) {
            _errorMessage.value = e.message
            Log.e(TAG, "Exception: $e")

        } finally {
            _isLoading.value = false
        }
    }

    val selectedDevice: MutableLiveData<Device> = MutableLiveData(
        Device(
            name = getStoredPreference(PREF_KEY_DEVICE) ?: "", id = null
        )
    )

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

    fun storeSelectedDevice(device: Device) {
        selectedDevice.value = device
        storePreference(PREF_KEY_DEVICE, device.name)
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
