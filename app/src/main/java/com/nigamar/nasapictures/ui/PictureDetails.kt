package com.nigamar.nasapictures.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nigamar.nasapictures.R
import timber.log.Timber

class PictureDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_details)
        val pictureId = intent.extras?.get("picture_id") as Int
        Timber.d("Id is $pictureId")
    }
}