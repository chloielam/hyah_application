package org.wit.placemark.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import org.wit.placemark.R
import org.wit.placemark.databinding.ActivityPlacemarkMapsBinding
import org.wit.placemark.databinding.ContentPlacemarkMapsBinding
import org.wit.placemark.main.MainApp
import android.graphics.PorterDuff


class PlacemarkMapsActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityPlacemarkMapsBinding
    private lateinit var contentBinding: ContentPlacemarkMapsBinding
    lateinit var map: GoogleMap
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp
        binding = ActivityPlacemarkMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contentBinding = ContentPlacemarkMapsBinding.bind(binding.root)
        contentBinding.mapView.onCreate(savedInstanceState)
        val bottomNavView = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavView.setOnNavigationItemSelectedListener(this)
        contentBinding.mapView.getMapAsync {
            map = it
            configureMap()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_map, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        contentBinding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        contentBinding.mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        contentBinding.mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        contentBinding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        contentBinding.mapView.onSaveInstanceState(outState)
    }

    private fun configureMap() {
        map.uiSettings.isZoomControlsEnabled = true
        app.placemarks.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options)?.tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
            map.setOnMarkerClickListener(this)
        }
    }
    override fun onMarkerClick(marker: Marker): Boolean {
        contentBinding.currentTitle.text = marker.title
        contentBinding.currentDescription.text = app.placemarks.getDescription(marker.tag as Long)
        val imageView = findViewById<ImageView>(R.id.imageView2)
        val currentImage = app.placemarks.getImage(marker.tag as Long)
        Picasso.get().load(currentImage).resize(250,250).into(imageView)
        return false
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


