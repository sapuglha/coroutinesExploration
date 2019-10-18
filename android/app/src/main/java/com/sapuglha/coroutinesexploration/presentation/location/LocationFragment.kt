package com.sapuglha.coroutinesexploration.presentation.location

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eazypermissions.common.model.PermissionResult
import com.eazypermissions.coroutinespermission.PermissionManager
import com.sapuglha.coroutinesexploration.R
import com.sapuglha.coroutinesexploration.databinding.FragmentLocationBinding
import com.sapuglha.coroutinesexploration.presentation.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: LocationViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_location, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestLocationPermissions()
    }

    private fun requestLocationPermissions() {
        CoroutineScope(Dispatchers.Default).launch {
            val permissionResult = PermissionManager.requestPermissions(
                this@LocationFragment,
                1337,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            when (permissionResult) {
                is PermissionResult.PermissionGranted -> viewModel.setPermission(true)
                is PermissionResult.PermissionDenied -> {
                    /*Add your logic to handle permission denial*/
                }
                is PermissionResult.PermissionDeniedPermanently -> {
                    //Add your logic here if user denied permission(s) permanently.
                    //Ideally you should ask user to manually go to settings and enable permission(s)
                }
                is PermissionResult.ShowRational -> {
                    //If user denied permission frequently then she/he is not clear about why you are asking this permission.
                    //This is your chance to explain them why you need permission.
                }
            }
        }
    }

    override fun onDestroy() {
        viewModel.stopLocationUpdates()
        super.onDestroy()
    }
}
