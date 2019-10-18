package com.sapuglha.coroutinesexploration.presentation.location

import android.location.Location
import androidx.lifecycle.*
import com.sapuglha.coroutinesexploration.domain.usecase.location.GetLastKnownLocationUseCase
import com.sapuglha.coroutinesexploration.domain.usecase.location.ObserveCurrentLocationUseCase
import com.sapuglha.coroutinesexploration.domain.usecase.location.StopLocationUpdatesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationViewModel @Inject constructor(
    private val getLastKnownLocationUseCase: GetLastKnownLocationUseCase,
    private val stopLocationUpdates: StopLocationUpdatesUseCase,
    private val observeCurrentLocationUseCase: ObserveCurrentLocationUseCase
) : ViewModel() {
    private val permissionGranted = MutableLiveData<Boolean>(false)

    private val lastKnownLocation = MutableLiveData<Location>().apply {
        viewModelScope.launch {
            value = getLastKnownLocationUseCase.execute()
        }
    }

    val lastLocationLabel = lastKnownLocation.map {
        "Latitude: ${it.latitude}, Longitude: ${it.longitude}"
    }


    val currentLocationLabel: LiveData<String> = liveData {
        emitSource(observeCurrentLocationUseCase.execute().map {
            "Latitude: ${it.latitude}, Longitude: ${it.longitude}"
        })
    }

    fun setPermission(granted: Boolean) {
        permissionGranted.postValue(granted)
    }

    fun onRefreshLocation() {
        viewModelScope.launch {
            lastKnownLocation.postValue(getLastKnownLocationUseCase.execute())
        }
    }

    fun stopLocationUpdates() {
        stopLocationUpdates.execute()
    }
}
