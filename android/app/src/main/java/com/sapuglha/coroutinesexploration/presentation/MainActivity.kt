package com.sapuglha.coroutinesexploration.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sapuglha.coroutinesexploration.R

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.bottom_menu_item_users,
                R.id.bottom_menu_item_location
            )
        )

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigation)

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Setup the bottom navigation
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.navFragment).navigateUp()
}
