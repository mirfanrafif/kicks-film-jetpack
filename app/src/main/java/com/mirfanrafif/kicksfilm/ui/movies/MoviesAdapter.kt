package com.mirfanrafif.kicksfilm.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mirfanrafif.kicksfilm.R
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.databinding.ItemMoviesBinding
import com.mirfanrafif.kicksfilm.ui.detail.DetailFilmActivity

class MoviesAdapter: PagedListAdapter<MovieEntity, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    class MoviesViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movieEntity: MovieEntity) {
            with(binding) {
                namaFilm.text = if (movieEntity.title.length > 20) "${movieEntity.title.take(20)}..." else movieEntity.title
                tahunFilm.text = movieEntity.year.toString()
                Glide.with(itemView).load(movieEntity.photo).placeholder(R.drawable.ic_baseline_broken_image_24).into(imgThumbnail)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_ID, movieEntity.id)
                    intent.putExtra(DetailFilmActivity.EXTRA_TYPE, "movie")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

}