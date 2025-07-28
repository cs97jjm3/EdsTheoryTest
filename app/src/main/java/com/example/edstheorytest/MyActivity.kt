package com.example.edstheorytest

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Your layout setup, e.g., setContentView(R.layout.activity_my)

        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // Do nothing here to prevent the activity from finishing
                // Or, you could navigate to a previous screen within your app
                // For example, if using Jetpack Navigation:
                // findNavController(R.id.nav_host_fragment).popBackStack()
                println("Back button pressed, but not exiting app.")
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }
}