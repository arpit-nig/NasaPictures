package com.nigamar.nasapictures

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.domain.use_cases.GetAllPictures
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetAllPicturesTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var fakePicturesRepository: FakePicturesRepository
    private lateinit var getAllPictures: GetAllPictures

    @Before
    fun setup(){
        fakePicturesRepository = FakePicturesRepository()
        getAllPictures = GetAllPictures(fakePicturesRepository)
    }

    @Test
    fun shouldReturnSortedListOfUrlsByDate() {
        val expectedList = mutableListOf<Picture>()
        val picture = Picture("test7", "2020-07-07", "test7", "test7", "test7", "test7", "test7", "test7").apply {
            id = 7
        }
        expectedList.add(picture)
        for (i in 2..12){
            if (i != 7){
                val picture1 = Picture(
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
                expectedList.add(picture1)
            } else {
                val picture2 = Picture("test1", "2020-01-01", "test1", "test1", "test1", "test1", "test1", "test1").apply {
                    id = 1
                }
                expectedList.add(picture2)
            }
        }

        runBlockingTest {
            val actualList = getAllPictures(7)
            Truth.assertThat(actualList).isEqualTo(expectedList)
        }
    }
}