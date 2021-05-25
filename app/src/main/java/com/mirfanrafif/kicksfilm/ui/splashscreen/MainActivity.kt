package com.mirfanrafif.kicksfilm.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.mirfanrafif.kicksfilm.R
import com.mirfanrafif.kicksfilm.databinding.ActivityMainBinding
import com.mirfanrafif.kicksfilm.ui.home.HomeActivity
import com.mirfanrafif.kicksfilm.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        EspressoIdlingResource.increment()
        lifecycleScope.launch(Dispatchers.Default) {
            delay(1000)
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            EspressoIdlingResource.decrement()
            finish()
        }
    }
}