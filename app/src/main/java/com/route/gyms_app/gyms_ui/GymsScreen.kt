package com.route.gyms_app.gyms_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.gyms_app.models.GymModel

@Composable
fun GymsScreen(){
    val gymsVM : GymsViewModel = viewModel()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
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
                items(gymsVM.state) { gym ->
                    CardView(gym) { gymId ->
                        gymsVM.triggerFavoriteState(gymId)
                    }
                }
            }
        }
    }
}

@Composable
fun CardView(gym: GymModel, onClick: (Int) -> Unit) {
    val icon = if (!gym.isFavorite) {
        Icons.Filled.FavoriteBorder
    } else {
        Icons.Filled.Favorite
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .height(100.dp)
        .background(color = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row ( verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier
               .fillMaxWidth()
               .padding(8.dp)){
            Box(
                Modifier
                    .weight(0.15f)
                    .padding(15.dp)
            ) {
                DefaultIcon(
                    icon = Icons.Filled.LocationOn,
                    contentDescription = "Location",
                    modifier = Modifier
                        .width(65.dp)
                        .height(30.dp)
                )
            }
            GymDetails(modifier = Modifier.weight(0.60f), gym =gym)
            DefaultIcon(
                contentDescription = "Favorite Icon",
                icon = icon,
                modifier = Modifier
                    .weight(0.20f)
                    .padding(8.dp),
                onClick = {
                    onClick(gym.id)
                })
        }
    }
}
@Composable
fun GymDetails(modifier: Modifier, gym: GymModel, alignment: Alignment.Horizontal=Alignment.Start){
    Column (modifier = modifier , horizontalAlignment = alignment){
        Text(text = gym.name,Modifier.padding(2.dp),
            style = MaterialTheme.typography.titleSmall,
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
@Composable
fun DefaultIcon(
    icon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit={},
    contentDescription: String,
) {
    Image(imageVector = icon,
        contentDescription = contentDescription,
        modifier
            .clickable {
                onClick()
            })
}

@Preview(showBackground = true)
@Composable
fun PreviewCard(){
    GymsScreen()
}