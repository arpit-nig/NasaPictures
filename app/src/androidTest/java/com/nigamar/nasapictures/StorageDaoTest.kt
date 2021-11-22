package com.nigamar.nasapictures

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.nigamar.nasapictures.common.Constants
import com.nigamar.nasapictures.data.dao.StorageDaoImpl
import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.data.data_source.PicturesDao
import com.nigamar.nasapictures.data.data_source.PicturesDb
import com.nigamar.nasapictures.domain.dao.StorageDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class StorageDaoTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private val scope = TestCoroutineScope(dispatcher)

    private lateinit var picturesDb : PicturesDb
    private lateinit var picturesDao: PicturesDao
    private lateinit var picturesList : List<Picture>
    private lateinit var  storageDao : StorageDao

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        picturesDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PicturesDb::class.java
        ).build()
        picturesDao = picturesDb.getPicturesDao()
        storageDao = StorageDaoImpl(ApplicationProvider.getApplicationContext() , picturesDao)
        val picture1 = Picture("test1","test1","test1","test1","test1","test1","test1","test1")
        val picture2 = Picture("test2","test2","test2","test2","test2","test2","test2","test2")
        picturesList = listOf(picture1,picture2)
    }


    @Test
    fun shouldSavePicturesListFromAssetsToDataSource(){
        scope.runBlockingTest {
            storageDao.saveDataInStorage(Constants.FILE_NAME_TEST)
            val actualList = picturesDao.getAllPictures()
            assertThat(actualList).isEqualTo(picturesList)
        }
    }
}