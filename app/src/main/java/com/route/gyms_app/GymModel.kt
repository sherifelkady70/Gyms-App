package com.route.gyms_app

data class GymModel(val id:Int , val title : String , val place : String,var isFavorite:Boolean=false)

object Gyms{
    val listOfGyms = listOf(
        GymModel(1,"none","the place of gym"),
        GymModel(2,"none","the place of gym"),
        GymModel(3,"none","the place of gym"),
        GymModel(4,"none","the place of gym"),
        GymModel(5,"none","the place of gym"),
        GymModel(6,"none","the place of gym"),
        GymModel(7,"none","the place of gym"),
    )
}
