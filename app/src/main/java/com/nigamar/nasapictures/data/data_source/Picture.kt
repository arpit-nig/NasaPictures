package com.nigamar.nasapictures.data.data_source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pictures_table")
data class Picture(
    var copyright: String?,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

class ComparePictures {
    companion object : Comparator<Picture> {
        override fun compare(p1: Picture, p2: Picture): Int {
            return (p1.id - p2.id)
        }
    }
}
