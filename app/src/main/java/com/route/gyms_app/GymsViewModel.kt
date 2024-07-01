package com.route.gyms_app

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class GymsViewModel(
    private val stateHandle:SavedStateHandle
) : ViewModel() {
    var state = mutableStateOf(restoreGymsListData())
    private fun getListOfGyms() : List<GymModel> {
        return Gyms.listOfGyms
    }

    fun triggerFavoriteState(gymId:Int){
        val gyms = state.value.toMutableList()
        val itemIndex = gyms.indexOfFirst { it.id==gymId }
        gyms[itemIndex] = gyms[itemIndex].copy(isFavorite = !gyms[itemIndex].isFavorite)
        saveGymsListData(gyms[itemIndex])
        state.value = gyms
    }

    private fun saveGymsListData(gym:GymModel){
        val stateHandleList = stateHandle.get<List<Int>>(FAV_KEY).orEmpty().toMutableStateList()
        if(gym.isFavorite) stateHandleList.add(gym.id)
        else stateHandleList.remove(gym.id)
        stateHandle[FAV_KEY] = stateHandleList
    }

    private fun restoreGymsListData():List<GymModel>{
        val gyms = getListOfGyms()
       stateHandle.get<List<Int>>(FAV_KEY)?.forEach { savedIds ->
           savedIds.let {
               gyms.find { it.id== savedIds }?.isFavorite=true
           }
       }
        return gyms
    }
    companion object{
        const val FAV_KEY = "favorite_key"
    }
}

