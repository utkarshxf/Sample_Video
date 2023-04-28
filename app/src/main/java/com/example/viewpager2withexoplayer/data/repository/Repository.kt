package com.mapper.videoreel.repository

import com.mapper.videoreel.api.ApiBuilder.retrofitBuilder
import com.mapper.videoreel.model.reels

class Repository {
    suspend fun getCall() : retrofit2.Response<reels>{
        return retrofitBuilder.getReels()
    }
}