package com.nigamar.nasapictures.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.domain.use_cases.GetAllPictures
import com.nigamar.nasapictures.domain.use_cases.GetPicturesByDate
import com.nigamar.nasapictures.ui.event.NasaPictureEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val getPicturesByDate: GetPicturesByDate,
    private val getAllPictures: GetAllPictures
) : ViewModel() {

    private val _pictures = MutableLiveData<List<Picture>>()
    val pictures = _pictures

    private fun getPicturesSortedByDate() = viewModelScope.launch {
        val response = getPicturesByDate()
        _pictures.postValue(response)
    }

    private fun getPictures(pictureId : Int) = viewModelScope.launch {
        val response = getAllPictures(pictureId)
        pictures.postValue(response)
    }

    fun loadState( nasaPicturesEvent: NasaPictureEvent ) {
        when(nasaPicturesEvent) {
            NasaPictureEvent.GetPicturesByDate -> {
                getPicturesSortedByDate()
            }
            is NasaPictureEvent.PictureDetailsEvent -> {
                getPictures(nasaPicturesEvent.pictureId)
            }
        }
    }
}