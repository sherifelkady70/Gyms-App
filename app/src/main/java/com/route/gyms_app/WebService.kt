package com.route.gyms_app

import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET("gyms.json")
    suspend fun getGymsList() : List<GymModel>

}