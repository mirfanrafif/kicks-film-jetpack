package com.mirfanrafif.kicksfilm.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import com.mirfanrafif.kicksfilm.R
import com.mirfanrafif.kicksfilm.databinding.ActivityHomeBinding
import com.mirfanrafif.kicksfilm.ui.favorite.FavoriteActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val tabName = resources.getStringArray(R.array.tab_name)

        val sectionPagesAdapter = SectionPagesAdapter(this)
        binding.viewpager.adapter = sectionPagesAdapter
        TabLayoutMediator(binding.tabs, binding.viewpager) {tabs, position ->
            tabs.text = tabName[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favoriteMenu -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
        }
        return true
    }
}