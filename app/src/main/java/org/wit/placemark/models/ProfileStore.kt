package org.wit.placemark.models

interface ProfileStore {
    fun find(): ProfileModel
    fun update(user: ProfileModel)
}