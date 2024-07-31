package com.route.gyms_app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    val retrofit: WebService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://cairo-gyms-e57d4-default-rtdb.firebaseio.com/")
        .build()
        .create(WebService::class.java)
}