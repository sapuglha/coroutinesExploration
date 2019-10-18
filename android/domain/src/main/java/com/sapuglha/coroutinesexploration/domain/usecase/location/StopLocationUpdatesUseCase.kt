package com.sapuglha.coroutinesexploration.domain.usecase.location

import com.sapuglha.coroutinesexploration.domain.repository.LocationRepository
import javax.inject.Inject

class StopLocationUpdatesUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    fun execute() = locationRepository.stopLocationUpdates()
}
