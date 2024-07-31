package com.route.gyms_app.gym_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.gyms_app.api.ApiManager
import com.route.gyms_app.models.GymModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GymDetailViewModel : ViewModel() {
    init {
        getGym(6)
    }
    var state = mutableStateOf<GymModel?>(null)
    private fun getGym(id: Int) {
        viewModelScope.launch {
            val gyms = getSpecificGymFromRemoteDB(id)
            state.value = gyms
        }
    }
    private suspend fun getSpecificGymFromRemoteDB(id : Int) = withContext(Dispatchers.IO){
        ApiManager.retrofit.getSpecificGym(id).values.first()
    }
}