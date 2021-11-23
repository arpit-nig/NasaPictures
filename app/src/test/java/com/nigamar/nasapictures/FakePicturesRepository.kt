package com.nigamar.nasapictures

import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.domain.repository.PicturesRepository

class FakePicturesRepository : PicturesRepository {

    override suspend fun getAllImages(): List<Picture> {
        val pictureList = mutableListOf<Picture>()
        for (i in 1..12) {
            val picture = Picture(
                "test$i",
                "2020-0$i-0$i",
                "test$i",
                "test$i",
                "test$i",
                "test$i",
                "test$i",
                "test$i"
            ).apply {
                id = i
            }
            pictureList.add(picture)
        }
        return pictureList
    }

    override suspend fun getPictureById(pictureId: Int): Picture {
        return Picture(
            "test$pictureId",
            "2020-0$pictureId-0$pictureId",
            "test$pictureId",
            "test$pictureId",
            "test$pictureId",
            "test$pictureId",
            "test$pictureId",
            "test$pictureId"
        ).apply {
            id = pictureId
        }
    }
}