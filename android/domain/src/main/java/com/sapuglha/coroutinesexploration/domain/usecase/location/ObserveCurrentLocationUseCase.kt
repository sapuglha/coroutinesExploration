package com.sapuglha.coroutinesexploration.domain.usecase.location

import android.location.Location
import androidx.lifecycle.LiveData
import com.sapuglha.coroutinesexploration.domain.repository.LocationRepository
import javax.inject.Inject

class ObserveCurrentLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend fun execute(): LiveData<Location> = locationRepository.observeLastLocation()
}
