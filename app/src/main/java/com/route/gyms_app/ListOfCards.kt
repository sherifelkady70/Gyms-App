package com.route.gyms_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ListOfCards(){
    val gymsVM : GymsViewModel = viewModel()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(6.dp)){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Text(text = "GYMS",
                color = Color.Blue,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp)
            LazyColumn {
                items(gymsVM.getListOfGyms()) {
                    CardView(it, modifier = Modifier)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListOfGyms(){
    ListOfCards()
}