package com.nigamar.nasapictures

import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.domain.repository.PicturesRepository

class FakePicturesRepository : PicturesRepository {

    override suspend fun getAllImages(): List<Picture> {
        val picture1 =
            Picture("test1", "2020-01-01", "test1", "test1", "test1", "test1", "test1", "test1")
        val picture2 =
            Picture("test2", "2021-01-01", "test2", "test2", "test2", "test2", "test2", "test2")
        return listOf(picture1, picture2)
    }
}