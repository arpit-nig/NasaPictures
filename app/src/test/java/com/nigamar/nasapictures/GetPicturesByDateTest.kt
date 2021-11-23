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
        val expectedList = mutableListOf<Picture>()
        for (i in 12 downTo 1){
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
        }
        runBlockingTest {
            val actualList = getPicturesByDate()
            assertThat(actualList).isEqualTo(expectedList)
        }
    }
}