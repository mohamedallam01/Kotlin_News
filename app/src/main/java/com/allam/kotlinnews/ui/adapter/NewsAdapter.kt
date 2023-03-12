package com.allam.kotlinnews.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.allam.kotlinnews.R
import com.allam.kotlinnews.model.ChildrenData
import com.allam.kotlinnews.model.getPrimaryThumbnailUrl
import com.allam.kotlinnews.ui.home.HomeFragmentDirections

class NewsAdapter() : ListAdapter<ChildrenData, NewsAdapter.NewsViewHolder>(NewsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


    class NewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        var txtTitle: TextView = view.findViewById(R.id.news_title)
        var image_movie: ImageView = view.findViewById(R.id.news_thumbnail)


        fun bindData(news: ChildrenData) {
            txtTitle.text = news.title
            val imageUrl = news.getPrimaryThumbnailUrl()
            if (imageUrl.isNullOrEmpty()) {
                image_movie.visibility = View.INVISIBLE
            } else{
                image_movie.visibility = View.VISIBLE
                image_movie.load(imageUrl)}
            view.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                        articleId = news.id,
                        articleTitle = news.title
                    )
                image_movie.findNavController().navigate(action)
            }
        }
    }


    class NewsDiffUtil : DiffUtil.ItemCallback<ChildrenData>() {
        override fun areItemsTheSame(oldItem: ChildrenData, newItem: ChildrenData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChildrenData, newItem: ChildrenData): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }

}