package com.nigamar.nasapictures.domain.use_cases

import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.domain.repository.PicturesRepository
import java.text.SimpleDateFormat
import java.util.*

class GetPicturesByDate constructor(
    private val picturesRepository: PicturesRepository
) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    suspend operator fun invoke(): List<Picture> {
        return picturesRepository.getAllImages().sortedByDescending {
            val date = dateFormat.parse(it.date)
            date.time
        }
    }
}