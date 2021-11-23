package com.nigamar.nasapictures.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nigamar.nasapictures.R
import com.nigamar.nasapictures.data.data_source.Picture
import kotlinx.android.synthetic.main.picture_item.view.*

class PicturesAdapter : RecyclerView.Adapter<PicturesAdapter.PicturesViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Picture>() {

        override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var pictureClickListener : ((Picture) -> Unit) ? = null

    fun getPictureAtPosition(listener : (Picture) -> Unit) {
        pictureClickListener = listener
    }

    val requestOptions = RequestOptions().placeholder(R.drawable.ic_placeholder)

    inner class PicturesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(picture: Picture) {
            // use glide to load the image
            Glide.with(itemView)
                .applyDefaultRequestOptions(requestOptions)
                .load(picture.url)
                .into(itemView.picture_view)
            // set the on click listener on the image
            itemView.setOnClickListener {
                pictureClickListener?.let {
                    it(picture)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        return PicturesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        val picture = differ.currentList[position]
        holder.bindData(picture)
    }

    override fun getItemCount() = differ.currentList.size
}