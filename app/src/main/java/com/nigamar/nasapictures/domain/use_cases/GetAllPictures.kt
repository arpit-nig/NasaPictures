package com.nigamar.nasapictures.domain.use_cases

import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.domain.repository.PicturesRepository

class GetAllPictures constructor (
    private val picturesRepository: PicturesRepository
) {
    suspend operator fun invoke() : List<Picture> {
        return picturesRepository.getAllImages()
    }
}