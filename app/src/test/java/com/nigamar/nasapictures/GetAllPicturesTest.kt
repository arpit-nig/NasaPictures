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
        val picture1 =
            Picture("test1", "2020-01-01", "test1", "test1", "test1", "test1", "test1", "test1")
        val picture2 =
            Picture("test2", "2021-01-01", "test2", "test2", "test2", "test2", "test2", "test2")
        val expectedList = listOf(picture1,picture2)

        runBlockingTest {
            val actualList = getAllPictures()
            Truth.assertThat(actualList).isEqualTo(expectedList)
        }
    }
}