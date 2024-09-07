package com.animation_app_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.animation_app_compose.states.DashboardState
import com.animation_app_compose.ui.theme.appColor
import com.animation_app_compose.ui.theme.greyTextColor

@Composable
fun ShipmentScreen(dashboardState: DashboardState) {

    val listItems = listOf(
        Status.Pending,
        Status.Pending,
        Status.InProgress,
        Status.Loading,
        Status.Loading,
        Status.Loading,
        Status.InProgress
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Shipment",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W500)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column() {
            when(dashboardState.shipmentSelected){
                0 ->  repeat(listItems.size){
                    Animation(delay = (50 * it.toLong()).plus(100), offset = 100){ ListItem(status = listItems[it]) }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                1 -> {
                    repeat(3){
                        Animation(delay = (50 * it.toLong()).plus(100),offset = 100){ ListItem(status = Status.Completed) }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                2 -> {
                    repeat(2){
                        Animation(delay = (50 * it.toLong()).plus(100),offset = 100){ ListItem(status = Status.InProgress) }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                3 -> {
                    repeat(4){
                        Animation(delay = (50 * it.toLong()).plus(100),offset = 100){ ListItem(status = Status.Pending) }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

            }
        }

    }
}


@Composable
@Preview
fun ListItem(status: Status = Status.Loading) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.2.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start, modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .background(
                            color = Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp),
                        )
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                ) {
                    Icon(
                        status.image,
                        contentDescription = "",
                        tint = status.statusColor.copy(0.5f),
                        modifier = Modifier.size(15.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(3.dp)
                    )

                    Text(
                        status.label,
                        style = TextStyle(
                            color = status.statusColor,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.W500
                        ),
                    )
                }
                Text(
                    "Arriving Today!", style = TextStyle(
                        fontSize = 16.sp, color = Color(0xFF424242), fontWeight = FontWeight.W600
                    ), modifier = Modifier.padding(vertical = 4.dp)
                )

                Text(
                    "Your delivery, #NEJ20089934122231 \nfrom Atlanta, is arriving today!",
                    style = TextStyle(
                        fontSize = 12.sp, color = Color(0xFF757575), fontWeight = FontWeight.Normal
                    ),
                )

                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = appColor, fontSize = 12.sp, fontWeight = FontWeight.W600
                        )
                    ) {
                        append("\$230 USD • ")
                    }


                    withStyle(
                        style = SpanStyle(
                            color = greyTextColor, fontSize = 11.sp, fontWeight = FontWeight.W400
                        )
                    ) {
                        append("Sept 20, 2023")
                    }
                }, modifier = Modifier.padding(top = 10.dp))

            }

            Image(
                painter = painterResource(id = R.drawable.parcel),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(120.dp)
            )

        }
    }
}


enum class Status(val label: String, val image: ImageVector, val statusColor: Color) {
    Pending(
        label = "Pending", image = Icons.Default.Refresh, statusColor = Color(0xFFFF8F00)
    ),
    Loading(label = "Loading", image = Icons.Default.Info, statusColor = Color.Blue), InProgress(
        label = "In progress", image = Icons.Default.Refresh, statusColor = Color(0xFF3F51B5)
    ),
    Completed(label = "Completed", image = Icons.Filled.Check, statusColor = Color(0xFF118804));

}
