package com.nigamar.nasapictures.data.repository

import com.nigamar.nasapictures.data.data_source.PicturesDao
import com.nigamar.nasapictures.domain.dao.StorageDao
import com.nigamar.nasapictures.domain.repository.PicturesRepository

class PicturesRepoImpl(
    private val storageDao: StorageDao,
    private val picturesDao: PicturesDao
) : PicturesRepository {

    override fun getAllImages() {
        TODO("Not yet implemented")
    }
}