package com.nigamar.nasapictures.domain.use_cases

import com.nigamar.nasapictures.domain.repository.PicturesRepository
import java.text.SimpleDateFormat
import java.util.*

class GetPicturesByDate constructor(
    private val picturesRepository: PicturesRepository
) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    suspend operator fun invoke(): List<String> {
        return picturesRepository.getAllImages().map {
            val date = dateFormat.parse(it.date)
            Pair(date.time, it.url)
        }.sortedByDescending {
            it.first
        }.map {
            it.second
        }
    }
}