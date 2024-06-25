package com.route.gyms_app

import androidx.lifecycle.ViewModel

class GymsViewModel : ViewModel() {
    fun getListOfGyms() : List<GymModel> {
        return Gyms.listOfGyms
    }
}