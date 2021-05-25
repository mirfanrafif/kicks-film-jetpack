package com.mirfanrafif.kicksfilm.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirfanrafif.kicksfilm.R
import com.mirfanrafif.kicksfilm.databinding.ActivityFavoriteBinding
import com.mirfanrafif.kicksfilm.viewmodel.ViewModelFactory

class FavoriteActivity : AppCompatActivity() {
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        val movieAdapter = FavoriteMovieAdapter()
        val layoutManager = LinearLayoutManager(this)
        val layoutManager2 = LinearLayoutManager(this)

        val tvAdapter = FavoriteTvShowAdapter()

        binding.rvFavoriteMovies.adapter = movieAdapter
        binding.rvFavoriteMovies.layoutManager = layoutManager

        binding.rvFavoriteTv.adapter = tvAdapter
        binding.rvFavoriteTv.layoutManager = layoutManager2

        viewModel.getFavoriteMovies().observe(this, {
            movieAdapter.submitList(it)
        })

        viewModel.getFavoriteTvShows().observe(this, {
            tvAdapter.submitList(it)
        })
    }
}