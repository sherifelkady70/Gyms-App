package com.route.gyms_app

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsViewModel(
    private val stateHandle:SavedStateHandle
) : ViewModel() {
    private var apiService : WebService
    private lateinit var gymsList : List<GymModel>
    private val errorHandle = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cairo-gyms-e57d4-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(WebService::class.java)
        getListOfGyms()
    }
    var state by mutableStateOf(emptyList<GymModel>())
    private fun getListOfGyms() {
        viewModelScope.launch(errorHandle) {
            withContext(Dispatchers.IO) {
                gymsList = apiService.getGymsList()
            }
            state = gymsList.restoreGymsListData()
        }
    }
    fun  triggerFavoriteState(gymId:Int){
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

