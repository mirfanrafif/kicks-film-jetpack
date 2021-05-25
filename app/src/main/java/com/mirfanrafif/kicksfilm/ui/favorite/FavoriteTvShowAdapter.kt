package com.mirfanrafif.kicksfilm.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mirfanrafif.kicksfilm.R
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.databinding.FavoriteItemBinding
import com.mirfanrafif.kicksfilm.ui.detail.DetailFilmActivity

class FavoriteTvShowAdapter: PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.FavoriteTvViewHolder>(
    DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class FavoriteTvViewHolder(private val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: TvShowEntity) {
            with(binding) {
                Glide.with(binding.root.context).load(entity.photo)
                    .placeholder(R.drawable.ic_baseline_broken_image_24).into(imgFavorite)
                tvFavoriteTitle.text = entity.title
                tvFavoriteYear.text = entity.year.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_ID, entity.id)
                    intent.putExtra(DetailFilmActivity.EXTRA_TYPE, "movie")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteTvShowAdapter.FavoriteTvViewHolder {
        val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteTvShowAdapter.FavoriteTvViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }
}