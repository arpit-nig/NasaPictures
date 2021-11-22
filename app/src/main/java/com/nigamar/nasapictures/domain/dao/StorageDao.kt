package com.nigamar.nasapictures.domain.dao

interface StorageDao {
    fun saveDataInStorage(data : String)
    fun isFirstLaunch() : Boolean
}