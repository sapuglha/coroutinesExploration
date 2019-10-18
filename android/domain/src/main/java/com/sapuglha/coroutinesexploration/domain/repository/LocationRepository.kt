package com.sapuglha.coroutinesexploration.domain.repository

import android.location.Location
import androidx.lifecycle.LiveData

interface LocationRepository {
    suspend fun getLastKnownLocation(): Location
    fun observeLastLocation(): LiveData<Location>
    fun stopLocationUpdates()
}
