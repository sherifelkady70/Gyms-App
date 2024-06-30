package com.route.gyms_app

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardView(gym:GymModel,modifier: Modifier) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .height(100.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row ( verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier
               .fillMaxWidth()
               .padding(8.dp)){
           Box(
               Modifier
                   .weight(0.15f)
                   .padding(15.dp)) {
               Icon(painter = painterResource(
                   id = R.drawable.baseline_location_on_24)
                   , contentDescription =null,
                   Modifier
                       .width(65.dp)
                       .height(30.dp))
           }
            Column (modifier = Modifier.weight(0.60f)){
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
            FavoriteIcon(Modifier.weight(0.20f))
        }
    }
}

@Composable
fun FavoriteIcon(modifier: Modifier) {
    var favoriteIconBoolean by remember { mutableStateOf(false) }
    val icon = if (!favoriteIconBoolean) {
        Icons.Filled.FavoriteBorder
    } else {
        Icons.Filled.Favorite
    }
    Image(imageVector = icon,
        contentDescription = "Favorite Icon",
        modifier.padding(8.dp)
            .clickable {
                favoriteIconBoolean = !favoriteIconBoolean
            })
}

@Preview(showBackground = true)
@Composable
fun PreviewCard(){
    CardView(GymModel("none","the place of gym"),modifier = Modifier)
}