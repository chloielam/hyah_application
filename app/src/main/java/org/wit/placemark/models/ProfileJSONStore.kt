package org.wit.placemark.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.placemark.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_PROFILE = "profile.json"
val gsonProfileBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val profileType: Type = object : TypeToken<ProfileModel>() {}.type

class ProfileJSONStore(private val context: Context): ProfileStore {
    var user = ProfileModel()

    init {
        if (exists(context, JSON_PROFILE)) {
            deserialize()
        }
    }

    override fun find(): ProfileModel {
        return user
    }

    override fun update(changed_user: ProfileModel) {
        user.name = changed_user.name
        user.username = changed_user.username
        user.pronouns = changed_user.pronouns
        user.birthday = changed_user.birthday
        user.description = changed_user.description
        user.avatar = changed_user.avatar
        serialize()
    }
    private fun serialize() {
        val jsonString = gsonProfileBuilder.toJson(user, profileType)
        write(context, JSON_PROFILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_PROFILE)
        user = gsonProfileBuilder.fromJson(jsonString, profileType)
    }
    class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Uri {
            return Uri.parse(json?.asString)
        }

        override fun serialize(
            src: Uri?,
            typeOfSrc: Type?,
            context: JsonSerializationContext?
        ): JsonElement {
            return JsonPrimitive(src.toString())
        }
    }
}
