package com.route.gyms_app.gym_details

import androidx.compose.runtime.Composable

@Composable
fun GymDetailScreen(){
    val viewModel = GymDetailViewModel()
    val item = viewModel.state.value
}