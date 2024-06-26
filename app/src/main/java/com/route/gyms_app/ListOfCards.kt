package com.route.gyms_app

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ListOfCards(){
    val gymsVM : GymsViewModel = viewModel()
    LazyColumn {
        items(gymsVM.getListOfGyms()){
            CardView(it,modifier = Modifier)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewListOfGyms(){
    ListOfCards()
}