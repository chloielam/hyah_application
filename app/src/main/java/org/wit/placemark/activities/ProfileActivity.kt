package org.wit.placemark.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.wit.placemark.R
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.Profile

class ProfileActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var app: MainApp
    var user: Profile = Profile()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp
        setContentView(R.layout.activity_profile)
        val bottomNavView = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // your implementation here
        when (item.itemId) {
            R.id.navigation_home -> {
                startActivity(Intent(this, PlacemarkMapsActivity::class.java))
                return true
            }
            R.id.navigation_placemarks -> {
                startActivity(Intent(this, PlacemarkListActivity::class.java))
                return true
            }
            R.id.navigation_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                return true
            }
        }
        return false
    }
}