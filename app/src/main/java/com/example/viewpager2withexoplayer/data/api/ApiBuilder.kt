package com.mapper.videoreel.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {
//    https://pixabay.com/api/videos/?key=35859382-17bea5724e8ce4ddd147ac17e&q=yellow+flowers

    val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://pixabay.com/")
            .build()
            .create(apiInterface::class.java)
}