package com.route.gyms_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CardView(modifier: Modifier) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
        Row (
            Modifier
                .fillMaxWidth()
                .padding(5.dp)){
            Icon(painter = painterResource(id = R.drawable.baseline_location_on_24), contentDescription =null)
            Column {
                Text(text = "Title")
                Text(text = "Address")
            }
        }
    }
}

