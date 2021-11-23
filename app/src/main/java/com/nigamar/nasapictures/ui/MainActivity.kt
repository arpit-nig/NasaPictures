package com.nigamar.nasapictures.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigamar.nasapictures.R
import com.nigamar.nasapictures.data.data_source.Picture
import com.nigamar.nasapictures.ui.adapter.PicturesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val picturesViewModel : PicturesViewModel by viewModels()

    private lateinit var picturesAdapter : PicturesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        picturesViewModel.loadState(1)
        observeChanges()
        listenForClickEvents()
    }

    private fun listenForClickEvents() {
        picturesAdapter.getPictureAtPosition { picture ->
            launchDetailsActivity(picture)
        }
    }

    private fun launchDetailsActivity(picture: Picture) {
        Intent(this,PictureDetails::class.java).apply {
            putExtra("picture_id",picture.id)
            startActivity(this)
        }
    }

    private fun observeChanges() {
        picturesViewModel.pictures.observe(this , {
            picturesAdapter.differ.submitList(it)
        })
    }

    private fun initView() {
        picturesAdapter = PicturesAdapter()
        pictures_view.apply {
            adapter = picturesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}