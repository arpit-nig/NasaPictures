package com.nigamar.nasapictures.data.dao

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nigamar.nasapictures.common.Constants.DOES_NOT_EXIST
import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.data.data_source.PicturesDao
import com.nigamar.nasapictures.domain.dao.StorageDao
import java.io.BufferedReader

class StorageDaoImpl constructor(
    private val context: Context ,
    private val picturesDao: PicturesDao
) : StorageDao {

    private val preferences by lazy {
        context.getSharedPreferences("pictures_pref",MODE_PRIVATE)
    }

    private val assetManager by lazy {
        context.assets
    }

    private val pictureListType = object : TypeToken<List<Picture>>(){}.type

    private val gson = Gson()

    override suspend fun saveDataInStorage(fileName: String) {
        val stream = assetManager.open(fileName) // get the stream from asset manager
        val content =
            stream.bufferedReader().use(BufferedReader::readText) // convert the stream to text
        // use gson to convert string into object
        val pictureList = gson.fromJson(content, pictureListType) as List<Picture>
        picturesDao.saveAllPictures(pictureList) // save the data in db
    }

    override fun isFirstLaunch(): Boolean {
        return preferences.getInt("first_launch",DOES_NOT_EXIST) == DOES_NOT_EXIST
    }

    override fun updateFirstLaunch(versionCode: Int) {
        preferences.edit().putInt("first_launch",versionCode).apply()
    }
}