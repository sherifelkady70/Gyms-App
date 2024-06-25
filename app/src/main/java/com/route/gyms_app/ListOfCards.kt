package com.route.gyms_app

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ListOfCards(){
    LazyColumn {
        items(Gyms.listOfGyms){
            CardView(it,modifier = Modifier)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewListOfGyms(){
    ListOfCards()
}