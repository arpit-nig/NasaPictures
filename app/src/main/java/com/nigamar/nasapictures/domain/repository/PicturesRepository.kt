package com.nigamar.nasapictures.domain.repository

import com.nigamar.nasapictures.data.data_source.Picture

interface PicturesRepository {
    suspend fun getAllImages() : List<Picture>
}