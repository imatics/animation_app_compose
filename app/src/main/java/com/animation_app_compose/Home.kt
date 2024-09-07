package com.animation_app_compose;


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun Home() {
    Column (modifier = Modifier
        .verticalScroll(rememberScrollState())){
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Tracking",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W500)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Animation(delay = 50) { TrackingCard() }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Available Vehicles",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W500)
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
        Row(Modifier.horizontalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.width(20.dp))
            Animation(delay = 50, Direction.Horizontal) {
                VehicleCard(
                    title = "Ocean freight",
                    "Internation",
                    R.drawable.ship,
                    index = 0
                )
            }
            Animation(delay = 100, Direction.Horizontal) {
                VehicleCard(
                    title = "Cargo freight",
                    "Reliable",
                    R.drawable.truck, index = 1
                )
            }

            Animation(delay = 150, Direction.Horizontal) {
                VehicleCard(
                    title = "Air freight",
                    "Internation",
                    R.drawable.plane,
                    index = 2
                )
            }
            Spacer(modifier = Modifier.width(20.dp))


        }
        Spacer(modifier = Modifier.height(100.dp))

    }
}
