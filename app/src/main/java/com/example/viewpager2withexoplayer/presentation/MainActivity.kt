package com.example.viewpager2withexoplayer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2withexoplayer.databinding.ActivityMainBinding
import com.example.viewpager2withexoplayer.domain.VideoAdapter
import com.example.viewpager2withexoplayer.domain.model.ExoPlayerItem
import com.example.viewpager2withexoplayer.domain.model.Video
import com.mapper.videoreel.viewmodel.ListViewModel
import com.mapper.videoreel.viewmodel.ListViewModelFactory
import com.orion.newsapp.mappers.toDomain

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel : ListViewModel

    private lateinit var adapter: VideoAdapter
    private var videos = ArrayList<Video>()
    private val exoPlayerItems = ArrayList<ExoPlayerItem>()
    public
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupInstances()

        fun callable(videos: ArrayList<Video>) {
            Log.d("qwerty", "callable: $videos")

            adapter = VideoAdapter(this, videos, object : VideoAdapter.OnVideoPreparedListener {
                override fun onVideoPrepared(exoPlayerItem: ExoPlayerItem) {
                    exoPlayerItems.add(exoPlayerItem)
                }
            })

            binding.viewPager2.adapter = adapter

            binding.viewPager2.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val previousIndex = exoPlayerItems.indexOfFirst { it.exoPlayer.isPlaying }
                    if (previousIndex != -1) {
                        val player = exoPlayerItems[previousIndex].exoPlayer
                        player.pause()
                        player.playWhenReady = false
                    }
                    val newIndex = exoPlayerItems.indexOfFirst { it.position == position }
                    if (newIndex != -1) {
                        val player = exoPlayerItems[newIndex].exoPlayer
                        player.playWhenReady = true
                        player.play()
                    }
                }
            })
        }
        myViewModel.myResponse.observe(this, Observer {
            videos = it.body()!!.hits.toDomain() as ArrayList<Video>
            callable(videos)
        })
    }

        override fun onPause() {
            super.onPause()

            val index =
                exoPlayerItems.indexOfFirst { it.position == binding.viewPager2.currentItem }
            if (index != -1) {
                val player = exoPlayerItems[index].exoPlayer
                player.pause()
                player.playWhenReady = false
            }
        }

        override fun onResume() {
            super.onResume()

            val index =
                exoPlayerItems.indexOfFirst { it.position == binding.viewPager2.currentItem }
            if (index != -1) {
                val player = exoPlayerItems[index].exoPlayer
                player.playWhenReady = true
                player.play()

            }
        }

        override fun onDestroy() {
            super.onDestroy()
            if (exoPlayerItems.isNotEmpty()) {
                for (item in exoPlayerItems) {
                    val player = item.exoPlayer
                    player.stop()
                    player.clearMediaItems()
                }
            }
        }

    private fun setupInstances(){
        myViewModel = ViewModelProvider(this , ListViewModelFactory())[ListViewModel::class.java]
        myViewModel.getCall()
    }
}