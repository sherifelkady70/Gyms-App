package com.route.gyms_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsViewModel(
    private val stateHandle:SavedStateHandle
) : ViewModel() {
    private var apiService : WebService
    private lateinit var gymsList : Call<List<GymModel>>
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://console.firebase.google.com/u/0/project/cairo-gyms-e57d4/database/cairo-gyms-e57d4-default-rtdb/data/~2F")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(WebService::class.java)
        getListOfGyms()
    }

    var state by mutableStateOf(emptyList<GymModel>())
    private fun getListOfGyms() {
        gymsList = apiService.getGymsList()
         gymsList.enqueue(object : Callback<List<GymModel>>{
             override fun onResponse(p0: Call<List<GymModel>>, p1: Response<List<GymModel>>) {
                 p1.body()?.let {
                     state = it.restoreGymsListData()
                 }
             }
             override fun onFailure(p0: Call<List<GymModel>>, p1: Throwable) {
                 p1.printStackTrace()
             }

         })
    }

    override fun onCleared() {
        super.onCleared()
        gymsList.cancel()
    }
    fun triggerFavoriteState(gymId:Int){
        val gyms = state.toMutableList()
        val itemIndex = gyms.indexOfFirst { it.id==gymId }
        gyms[itemIndex] = gyms[itemIndex].copy(isFavorite = !gyms[itemIndex].isFavorite)
        saveGymsListData(gyms[itemIndex])
        state = gyms
    }

    private fun saveGymsListData(gym:GymModel){
        val stateHandleList = stateHandle.get<List<Int>>(FAV_KEY).orEmpty().toMutableStateList()
        if(gym.isFavorite) stateHandleList.add(gym.id)
        else stateHandleList.remove(gym.id)
        stateHandle[FAV_KEY] = stateHandleList
    }

    private fun List<GymModel>.restoreGymsListData():List<GymModel>{
        val gyms = this
       stateHandle.get<List<Int>>(FAV_KEY)?.let { savedIds ->
           savedIds.forEach { gymsIds ->
               gyms.find { it.id== gymsIds }?.isFavorite=true
           }
       }
        return gyms
    }
    companion object{
        const val FAV_KEY = "favorite_key"
    }
}

