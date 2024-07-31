package com.route.gyms_app.api

import com.route.gyms_app.models.GymModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("gyms.json")
    suspend fun getGymsList() : List<GymModel>

    @GET("gyms.json?orderBy=\"id\"")
    suspend fun getSpecificGym(
        @Query("equalTo") id : Int
    ) : Map<String, GymModel>
}