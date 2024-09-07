package com.animation_app_compose

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.animation_app_compose.states.DashboardState
import com.animation_app_compose.ui.theme.orangeColor
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Estimate(dashboardState: DashboardState) {

    BackHandler(true) {
      dashboardState.showRoute(Routes.Start)
    }

    Scaffold(
        containerColor = Color(0xFFFAFAFA)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Animation(delay = 100) {
                Image(
                    painter = painterResource(id = R.drawable.move_mate),
                    contentDescription = "",
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                )
            }

            Image(painter = painterResource(id = R.drawable.parcel_big), contentDescription = "")
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Animation(delay = 200) {
                    Text(
                        text = "Total Estimated Amount",
                        style = TextStyle(color = Color(0xFF444444), fontSize = 25.sp)
                    )
                }
                Animation(delay = 300) { GrowingAmount(1347, 2000) }
                Animation(delay = 400) {
                    Text(
                        text = "This amount is estimated this will vary \nif you change your location or weight",
                        style = TextStyle(color = Color(0xFF444444), fontSize = 16.sp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Animation(delay = 500, duration = 1200){
                Button(
                    onClick = {
                        dashboardState.showRoute(Routes.Start)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = orangeColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = "Back to home")
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }


}


@Composable
fun GrowingAmount(amount: Int, duration: Long) {

    val amountValue = remember { mutableIntStateOf(0) }
    val count = remember { mutableIntStateOf(0) }

    val sequence: Int =
        ((amount * 0.8) / (duration / 31)).toInt()


    LaunchedEffect(amountValue.intValue) {
        if (amount != amountValue.intValue) {
            delay(10)
            val current: Int = sequence * count.intValue
            if ((amountValue.intValue + current) > amount) {
                amountValue.intValue = amount
            } else {
                amountValue.intValue += sequence
            }
            count.intValue += 1
        }
    }


    Text(text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color(0xFF2CB307),
                fontSize = 21.sp,
                fontWeight = FontWeight.W400
            )
        ) {
            append("$${amountValue.intValue}")
        }

        withStyle(style = SpanStyle(color = Color(0xFF2CB307), fontSize = 18.sp)) {
            append("USD")
        }
    }, modifier = Modifier.padding(vertical = 10.dp))


}
