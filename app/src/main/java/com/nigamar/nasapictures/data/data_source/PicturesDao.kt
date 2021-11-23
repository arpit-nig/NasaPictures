package com.nigamar.nasapictures.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PicturesDao {

    @Insert
    suspend fun saveAllPictures(picturesList : List<Picture>)

    @Query("select * from pictures_table")
    suspend fun getAllPictures() : List<Picture>

    @Query("select * from pictures_table where id = :pictureId")
    suspend fun getPictureById(pictureId: Int): Picture
}