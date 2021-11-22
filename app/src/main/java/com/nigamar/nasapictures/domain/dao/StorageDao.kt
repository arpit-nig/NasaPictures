package com.nigamar.nasapictures.domain.dao

interface StorageDao {
    suspend fun saveDataInStorage(fileName : String)
    fun isFirstLaunch() : Boolean
    fun updateFirstLaunch( versionCode : Int)
}