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
import kotlinx.android.synthetic.main.picture_details_item.view.*

class PictureDetailsAdapter : RecyclerView.Adapter<PictureDetailsAdapter.PagerViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Picture>() {

        override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(picture: Picture) {
            Glide.with(itemView)
                .load(picture.hdurl)
                .placeholder(R.drawable.moon_animated)
                .into(itemView.picture)
            itemView.apply {
                title.text = picture.title
                copyright.text = picture.copyright?.let {
                    "-- $it"
                } ?: "-- Unknown"
                explanation.text = picture.explanation
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.picture_details_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val picture = differ.currentList[position]
        holder.bindData(picture)
    }

    override fun getItemCount() = differ.currentList.size
}