package com.mapper.videoreel.api

import com.mapper.videoreel.model.reels
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query



interface apiInterface {
    //    https://pixabay.com/api/videos/?key=35859382-17bea5724e8ce4ddd147ac17e&q=yellow+flowers

    @GET("api/videos/")
   suspend  fun getReels(
        @Query("key") apiKey:String = "35859382-17bea5724e8ce4ddd147ac17e",
        @Query("q") country:String = "yellow+flowers"
   ): retrofit2.Response<reels>
}