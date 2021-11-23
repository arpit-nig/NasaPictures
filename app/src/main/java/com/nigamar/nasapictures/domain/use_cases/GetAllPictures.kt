package com.nigamar.nasapictures.domain.use_cases

import com.nigamar.nasapictures.data.data_source.ComparePictures
import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.domain.repository.PicturesRepository
import timber.log.Timber

class GetAllPictures constructor (
    private val picturesRepository: PicturesRepository
) {
    suspend operator fun invoke(pictureId : Int) : List<Picture> {
        val picture = picturesRepository.getPictureById(pictureId) // get picture by id
        val pictureList =  picturesRepository.getAllImages().toMutableList() // get all pictures
        // if this is the first picture already then return the list
        if (pictureList[0].id == picture.id) return pictureList
        val position = pictureList.binarySearch(picture,ComparePictures) // find the picture in in the list
        // swap the position
        val temp = pictureList[position]
        pictureList[position] = pictureList[0]
        pictureList[0] = temp
        return pictureList
    }
}