package com.nigamar.nasapictures

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.data.data_source.PicturesDao
import com.nigamar.nasapictures.data.data_source.PicturesDb
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var picturesDb : PicturesDb
    private lateinit var picturesDao: PicturesDao
    private lateinit var picturesList : List<Picture>

    @Before
    fun setup(){
        picturesDb = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PicturesDb::class.java
        ).build()
        picturesDao = picturesDb.getPicturesDao()
        val picture1 = Picture("test1","test1","test1","test1","test1","test1","test1","test1")
        val picture2 = Picture("test2","test2","test2","test2","test2","test2","test2","test2")
        picturesList = listOf(picture1,picture2)
    }

    @Test
    fun onQuery_shouldReturnAllPictures() {
        runBlockingTest {
            picturesDao.saveAllPictures(picturesList)
            val actualList = picturesDao.getAllPictures()
            assertThat(actualList).isEqualTo(picturesList)
        }
    }
}