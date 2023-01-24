package org.wit.placemark.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.placemark.R
import org.wit.placemark.databinding.ActivityProfileEditBinding
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.ProfileModel
import org.wit.placemark.showImagePicker
import timber.log.Timber

class ProfileEditActivity : AppCompatActivity() {
    lateinit var app: MainApp
    var user = ProfileModel()
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityProfileEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_profile_edit)
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

        binding.buttonSave.setOnClickListener() {
            user.name = binding.rname.text.toString()
            user.username = binding.ruser.text.toString()
            user.pronouns = binding.rpronouns.text.toString()
            user.birthday = binding.rbirthday.text.toString()
            user.description = binding.rdescription.text.toString()
            if (user.username.isEmpty()) {
                Snackbar.make(it,R.string.enter_username, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                app.profile.update(user.copy())
            }
            Timber.i("Save Button Pressed: $user")
            setResult(RESULT_OK)
            finish()
        }

        binding.buttonAvatar.setOnClickListener {
            showImagePicker(imageIntentLauncher, this)
        }
        binding.buttonCancel.setOnClickListener() {
            finish()
        }
        registerImagePickerCallback()
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            Timber.i("Got Result ${result.data!!.data}")
                            val image = result.data!!.data!!
                            contentResolver.takePersistableUriPermission(image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            user.avatar = image
                            Picasso.get()
                                .load(user.avatar)
                                .into(binding.avatar)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

}