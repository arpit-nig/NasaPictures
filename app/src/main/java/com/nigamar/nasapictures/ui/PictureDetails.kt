package com.nigamar.nasapictures.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.nigamar.nasapictures.R
import com.nigamar.nasapictures.common.Constants.PICTURE_ID
import com.nigamar.nasapictures.ui.adapter.PictureDetailsAdapter
import com.nigamar.nasapictures.ui.event.NasaPictureEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_picture_details.*
import timber.log.Timber

@AndroidEntryPoint
class PictureDetails : AppCompatActivity() {

    private val picturesViewModel : PicturesViewModel by viewModels()

    private lateinit var pictureDetailsAdapter: PictureDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
        val pictureId = intent.extras?.get(PICTURE_ID) as Int
        picturesViewModel.loadState(NasaPictureEvent.PictureDetailsEvent(pictureId))
        observeStateChanges()
    }

    private fun observeStateChanges() {
        picturesViewModel.pictures.observe(this , {
            pictureDetailsAdapter.differ.submitList(it)
        })
    }

    private fun initView() {
        pictureDetailsAdapter = PictureDetailsAdapter()
        view_pager.apply {
            adapter = pictureDetailsAdapter
        }
        page_indicator.setViewPager(view_pager)
        pictureDetailsAdapter.registerAdapterDataObserver(page_indicator.adapterDataObserver)
    }
}