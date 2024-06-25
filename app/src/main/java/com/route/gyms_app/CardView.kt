package com.route.gyms_app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardView(gym:GymModel,modifier: Modifier) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .height(100.dp)) {
        Row ( verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier
               .fillMaxWidth()
               .padding(8.dp)){
           Box(
               Modifier
                   .weight(0.25f)
                   .padding(15.dp)) {
               Icon(painter = painterResource(
                   id = R.drawable.baseline_location_on_24)
                   , contentDescription =null,
                   Modifier
                       .width(65.dp)
                       .height(30.dp))
           }
            Column (modifier = Modifier.weight(0.75f)){
                Text(text = gym.title,Modifier.padding(2.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Blue)
                CompositionLocalProvider (
                    LocalContentColor provides LocalContentColor.current
                ) {
                    Text(text = gym.place,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCard(){
    CardView(
        GymModel("none","the place of gym"),modifier = Modifier)
}