package com.mirfanrafif.kicksfilm.ui.detail

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mirfanrafif.kicksfilm.R
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.databinding.ActivityDetailFilmBinding
import com.mirfanrafif.kicksfilm.viewmodel.ViewModelFactory
import com.mirfanrafif.kicksfilm.vo.Status

class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var binding: ActivityDetailFilmBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            binding.toolbar.setTitleTextColor(getColor(R.color.white))
        }else{
            binding.toolbar.setTitleTextColor(Color.parseColor("#ffffffff"))
        }

        setSupportActionBar(binding.toolbar)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        getData(type, id)
    }

    private fun getData(type: String?, id: Int) {
        when(type) {
            "movie" -> {
                viewModel.getDetailMovie(id).observe(this, { result ->
                    if (result != null) {
                        when(result.status) {
                            Status.LOADING -> binding.detailLoading.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.detailLoading.visibility = View.GONE
                                bindDataToView(result.data)
                            }
                            Status.ERROR -> {
                                binding.detailLoading.visibility = View.GONE
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
            "tvshow" -> {
                viewModel.getTvShow(id).observe(this, { result ->
                    if (result != null) {
                        when(result.status) {
                            Status.LOADING -> binding.detailLoading.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.detailLoading.visibility = View.GONE
                                bindDataToView(result.data)
                            }
                            Status.ERROR -> {
                                binding.detailLoading.visibility = View.GONE
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
            else -> Log.e("DetailFilmActivity", "Unknown data type")
        }
    }

    private fun <T> bindDataToView(data: T) {
        when(data) {
           is MovieEntity -> {
               Glide.with(this@DetailFilmActivity).load(data.photo).into(binding.imgDetail)
               val rating = data.rating.times(10).toInt()
               supportActionBar?.title = data.title
               binding.contentDetail.textTahun.text = data.year.toString()
               binding.contentDetail.textCategory.text = data.category
               binding.contentDetail.textDescription.text = data.overview
               binding.contentDetail.ratingBar.max = 100
               binding.contentDetail.ratingBar.progress = data.rating.times(10).toInt()
               binding.contentDetail.textRating.text = getString(R.string.rating, rating)
               binding.favoriteFab.setImageResource(
                   if(data.isFavorite) R.drawable.ic_baseline_favorite_24
                   else R.drawable.ic_baseline_favorite_border_24
               )
               binding.favoriteFab.setOnClickListener {
                   data.isFavorite = !data.isFavorite
                   viewModel.updateMovie(data)
               }
           }
           is TvShowEntity -> {
               Glide.with(this@DetailFilmActivity).load(data.photo).into(binding.imgDetail)
               val rating = data.rating.times(10).toInt()
               supportActionBar?.title = data.title
               binding.contentDetail.textTahun.text = data.year.toString()
               binding.contentDetail.textCategory.text = data.category
               binding.contentDetail.textDescription.text = data.overview
               binding.contentDetail.ratingBar.max = 100
               binding.contentDetail.ratingBar.progress = data.rating.times(10).toInt()
               binding.contentDetail.textRating.text = getString(R.string.rating, rating)
               binding.favoriteFab.setImageResource(
                   if(data.isFavorite) R.drawable.ic_baseline_favorite_24
                   else R.drawable.ic_baseline_favorite_border_24
               )
               binding.favoriteFab.setOnClickListener {
                   data.isFavorite = !data.isFavorite
                   viewModel.updateTvShow(data)
               }
           }
           else -> Log.e("DetailFilmActivity", "Unknown data type")
        }
    }
}