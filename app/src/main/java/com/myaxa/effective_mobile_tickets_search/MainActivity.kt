package com.myaxa.effective_mobile_tickets_search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.myaxa.effective_mobile_tickets_search.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        //hack for highlighting the main menu item
        navController.addOnDestinationChangedListener { controller, destination, args ->
            binding.navView.menu[0].setChecked(true)
        }

        binding.navView.setupWithNavController(navController)
    }
}