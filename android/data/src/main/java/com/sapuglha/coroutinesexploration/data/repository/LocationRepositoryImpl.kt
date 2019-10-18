package com.sapuglha.coroutinesexploration.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.sapuglha.coroutinesexploration.domain.repository.LocationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Permission should be handled by the activity/fragment before making use of this repository
 */
class LocationRepositoryImpl @Inject constructor(
    private val context: Context
) : LocationRepository {

    private lateinit var listener: LocationCallback

    init {
        startLocationUpdates()
    }

    private val currentLocation = MutableLiveData<Location>()

    override fun observeLastLocation(): LiveData<Location> = currentLocation

    @SuppressLint("MissingPermission")
    override suspend fun getLastKnownLocation(): Location = suspendCoroutine { coroutine ->
        LocationServices.getFusedLocationProviderClient(context).lastLocation
            .addOnSuccessListener { location -> coroutine.resume(location) }
            .addOnCanceledListener { Timber.e("Last Known location: on canceled listener") }
            .addOnFailureListener { Timber.e("Last Known location: on failure listener") }
    }

    override fun stopLocationUpdates() {
        val locationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        locationProviderClient.removeLocationUpdates(listener)
    }

    private fun startLocationUpdates() {
        val locationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        val locationRequest = LocationRequest().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            fastestInterval = 0
            interval = 0
        }

        listener = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.lastLocation?.let {
                    currentLocation.postValue(it)
                    Timber.d("New fresh location $it")
                }
            }
        }

        locationProviderClient
            .requestLocationUpdates(locationRequest, listener, Looper.getMainLooper())
    }
}
