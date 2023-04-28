package com.orion.newsapp.mappers

import com.example.viewpager2withexoplayer.domain.model.Video
import com.mapper.videoreel.model.Hit


//toDomain will me called by api impl
fun List<Hit>.toDomain():List<Video>{
    return map {
        Video(
            url = it.videos.tiny.url?:"",
            title = it.user?:"",
        )
    }
}