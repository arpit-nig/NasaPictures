package com.nigamar.nasapictures

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.domain.use_cases.GetAllPictures
import com.nigamar.nasapictures.domain.use_cases.GetPicturesByDate
import com.nigamar.nasapictures.ui.PicturesViewModel
import com.nigamar.nasapictures.ui.event.NasaPictureEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PicturesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val dispatcher = TestCoroutineDispatcher()

    private lateinit var fakePicturesRepository: FakePicturesRepository
    private lateinit var getAllPictures: GetAllPictures
    private lateinit var getPicturesByDate: GetPicturesByDate
    private lateinit var picturesViewModel : PicturesViewModel

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        fakePicturesRepository = FakePicturesRepository()
        getAllPictures = GetAllPictures(fakePicturesRepository)
        getPicturesByDate = GetPicturesByDate(fakePicturesRepository)
        picturesViewModel = PicturesViewModel(getPicturesByDate,getAllPictures)
    }

    @Test
    fun onEvent_GetPicturesByDate_shouldLoadPicturesByDate(){
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
            picturesViewModel.loadState(NasaPictureEvent.GetPicturesByDate)
            val actualList = picturesViewModel.pictures.getOrAwaitValue()
            assertThat(actualList).isEqualTo(expectedList)
        }
    }

    @Test
    fun onEvent_PictureDetailsEvent_shouldLoadTheFirstPageAsThePassedId(){
        val testId = 5
        runBlockingTest {
            val expectedItemOnTopOfList = fakePicturesRepository.getPictureById(testId)
            picturesViewModel.loadState(NasaPictureEvent.PictureDetailsEvent(testId))
            val actualList = picturesViewModel.pictures.getOrAwaitValue()
            assertThat(actualList[0]).isEqualTo(expectedItemOnTopOfList)
        }
    }
}