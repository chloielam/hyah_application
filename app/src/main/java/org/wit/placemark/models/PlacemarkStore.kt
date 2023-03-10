package org.wit.placemark.models

import android.net.Uri

interface PlacemarkStore {
    fun findAll(): List<PlacemarkModel>
    fun create(placemark: PlacemarkModel)
    fun update(placemark: PlacemarkModel)
    fun delete(placemark: PlacemarkModel)
    fun getDescription(id: Long): String
    fun getImage(id: Long): Uri
}