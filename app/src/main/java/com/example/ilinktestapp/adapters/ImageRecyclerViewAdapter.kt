package com.example.ilinktestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ilinktestapp.databinding.ImageItemBinding
import com.example.ilinktestapp.db.Image
import com.example.ilinktestapp.db.ImageDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ImageRecyclerViewAdapter @Inject constructor(
    private val imageDao: ImageDao
)
    : RecyclerView.Adapter<ImageRecyclerViewAdapter.ImageViewHolder> () {
    inner class ImageViewHolder
        (val binding: ImageItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    private val listOfImages : MutableList<Image> = mutableListOf()

    fun setImages (images: List<Image>) {
        listOfImages.clear()
        listOfImages.addAll(images)
        notifyDataSetChanged()
    }

    fun clearAllImages() {
        listOfImages.clear()
    }


    fun addImage (images: Image) {
        listOfImages.add(images)
        notifyItemChanged(listOfImages.size-1)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder
        = ImageViewHolder(
                ImageItemBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.binding.apply {
            imageView.setImageBitmap(listOfImages[position].image)
            dislikeButton.setOnClickListener {
                imageDao.deleteImage(listOfImages[position])
            }
        }
    }

    override fun getItemCount() = listOfImages.size
}