package org.wit.placemark.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ProfileModel(var name: String = "",
                   var username: String = "",
                   var pronouns: String = "",
                   var birthday: String = "",
                   var description: String = "",
                   var avatar: Uri = Uri.EMPTY)