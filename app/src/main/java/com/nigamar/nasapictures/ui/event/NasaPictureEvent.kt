package com.nigamar.nasapictures.ui.event

sealed class NasaPictureEvent {
    data class PictureDetailsEvent(val pictureId : Int) : NasaPictureEvent()
    object GetPicturesByDate : NasaPictureEvent()
}
