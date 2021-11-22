package com.nigamar.nasapictures.domain.use_cases

import com.nigamar.nasapictures.domain.repository.PicturesRepository

class GetPicturesByDate constructor(
    private val picturesRepository: PicturesRepository
) {
}