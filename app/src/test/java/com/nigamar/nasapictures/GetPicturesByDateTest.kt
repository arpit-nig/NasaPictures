package com.nigamar.nasapictures

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.domain.use_cases.GetPicturesByDate
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetPicturesByDateTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var fakePicturesRepository: FakePicturesRepository
    private lateinit var getPicturesByDate: GetPicturesByDate

    @Before
    fun setup(){
        fakePicturesRepository = FakePicturesRepository()
        getPicturesByDate = GetPicturesByDate(fakePicturesRepository)
    }

    @Test
    fun shouldReturnSortedListOfPicturesByDate() {
        val picture1 =
            Picture("test1", "2020-01-01", "test1", "test1", "test1", "test1", "test1", "test1")
        val picture2 =
            Picture("test2", "2021-01-01", "test2", "test2", "test2", "test2", "test2", "test2")
        val expectedList = listOf(picture2,picture1)
        runBlockingTest {
            val actualList = getPicturesByDate()
            assertThat(actualList).isEqualTo(expectedList)
        }
    }
}