package com.sapuglha.coroutinesexploration.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sapuglha.coroutinesexploration.R
import com.sapuglha.coroutinesexploration.databinding.ActivityMainBinding
import com.sapuglha.coroutinesexploration.presentation.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        // Setup the recyclerView list
        binding.listUsers.layoutManager = LinearLayoutManager(this)
        binding.listUsers.hasFixedSize()
        binding.listUsers.adapter = UsersAdapter()

        binding.floatingActionButton.setOnClickListener {
            viewModel.updateUser()
        }
    }
}
