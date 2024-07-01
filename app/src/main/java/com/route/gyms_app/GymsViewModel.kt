package com.route.gyms_app

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GymsViewModel : ViewModel() {
    var state = mutableStateOf(getListOfGyms())
    private fun getListOfGyms() : List<GymModel> {
        return Gyms.listOfGyms
    }

    fun triggerFavoriteState(gymId:Int){
        val gyms = state.value.toMutableList()
        val itemIndex = gyms.indexOfFirst { it.id==gymId }
        gyms[itemIndex] = gyms[itemIndex].copy(isFavorite = !gyms[itemIndex].isFavorite)
        state.value = gyms
    }
}