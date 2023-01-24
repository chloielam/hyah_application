package org.wit.placemark.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import org.wit.placemark.R
import org.wit.placemark.databinding.ActivityProfileBinding
import org.wit.placemark.databinding.ActivityProfileEditBinding
import org.wit.placemark.main.MainApp
//import org.wit.placemark.models.Profile

class ProfileActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var app: MainApp
    private lateinit var binding: ActivityProfileBinding
    //var user: Profile = Profile()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp
        // setContentView(R.layout.activity_profile)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app = application as MainApp
        binding.rname.setText(app.profile.find().name)
        binding.ruser.setText(app.profile.find().username)
        binding.rpronouns.setText(app.profile.find().pronouns)
        binding.rdescription.setText(app.profile.find().description)
        binding.rbirthday.setText(app.profile.find().birthday)
        if(app.profile.find().avatar != Uri.EMPTY) {
            Picasso.get()
                .load(app.profile.find().avatar)
                .into(binding.avatar)
        }
        val profileEditButton = findViewById<Button>(R.id.button_edit)
        profileEditButton.setOnClickListener {
            val launcherIntent = Intent(this, ProfileEditActivity::class.java)
            getResult.launch(launcherIntent)
        }
        val bottomNavView = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavView.setOnNavigationItemSelectedListener(this)
    }
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                binding.rname.text = app.profile.find().name
                binding.ruser.text = app.profile.find().username
                binding.rpronouns.text = app.profile.find().pronouns
                binding.rbirthday.text = app.profile.find().birthday
                binding.rdescription.text = app.profile.find().description
                if(app.profile.find().avatar != Uri.EMPTY) {
                Picasso.get()
                    .load(app.profile.find().avatar)
                    .into(binding.avatar)
                }
            }
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