package com.mirfanrafif.kicksfilm.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.databinding.FragmentTvShowBinding
import com.mirfanrafif.kicksfilm.viewmodel.ViewModelFactory
import com.mirfanrafif.kicksfilm.vo.Status

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTvShowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(requireActivity(), factory)[TvShowViewModel::class.java]
            val adapter = TvShowAdapter()
            val layoutManager = GridLayoutManager(context, 3)

            binding.rvTvShows.adapter = adapter
            binding.rvTvShows.layoutManager = layoutManager
            viewModel.getAllTvShow().observe(this, {
                if (it != null) {
                    when(it.status) {
                        Status.LOADING -> binding.tvShowLoading.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            adapter.submitList(it.data)
                            binding.tvShowLoading.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            binding.tvShowLoading.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

        }
    }
}