package com.nigamar.nasapictures.data.repository

import com.nigamar.nasapictures.BuildConfig
import com.nigamar.nasapictures.common.Constants
import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.data.data_source.PicturesDao
import com.nigamar.nasapictures.domain.dao.StorageDao
import com.nigamar.nasapictures.domain.repository.PicturesRepository

class PicturesRepoImpl(
    private val storageDao: StorageDao,
    private val picturesDao: PicturesDao
) : PicturesRepository {

    override suspend fun getAllImages(): List<Picture> {
        if (storageDao.isFirstLaunch()) {
            storageDao.saveDataInStorage(Constants.FILE_NAME)
            storageDao.updateFirstLaunch(BuildConfig.VERSION_CODE)
        }
        return picturesDao.getAllPictures()
    }

    override suspend fun getPictureById(pictureId: Int): Picture {
        return picturesDao.getPictureById(pictureId)
    }
}