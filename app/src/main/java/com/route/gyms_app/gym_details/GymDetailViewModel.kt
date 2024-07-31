package com.route.gyms_app.gym_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.gyms_app.api.ApiManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GymDetailViewModel : ViewModel() {

    init {
        getGym(6)
    }

    private fun getGym(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            ApiManager.retrofit.getSpecificGym(id)
        }
    }
}