package com.nigamar.nasapictures.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Picture::class],
    version = 1
)
abstract class PicturesDb : RoomDatabase(){
    abstract fun getPicturesDao() : PicturesDao
}