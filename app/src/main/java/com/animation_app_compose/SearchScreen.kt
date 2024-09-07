package com.animation_app_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.animation_app_compose.ui.theme.appColor
import com.animation_app_compose.ui.theme.greyTextColor


@Preview
@Composable
fun SearchScreen() {
    Column(modifier = Modifier.padding(15.dp)){
        Animation(delay = 100, duration = 800) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    Spacer(modifier = Modifier.height(10.dp))
                    repeat(4) {
                        Animation(delay = 100L * it, duration = 1200, offset = 10) {
                            Spacer(modifier = Modifier.height(40.dp))
                            ListItem()
                            Spacer(modifier = Modifier.height(40.dp))
                        }
                        if (it < 3) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0xFFF1F1F1))
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}


@Composable
@Preview
fun ListItem(){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(color = Color.White)
        .padding(vertical = 10.dp, horizontal = 5.dp), verticalAlignment = Alignment.CenterVertically){
        Box(modifier = Modifier
            .background(appColor, shape = CircleShape)
            .padding(14.dp)) {
            Icon(
                Icons.Default.AccountBox,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = "Macbook Pro 2", style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.W500, color = greyTextColor))
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "#NHEU3384903 • Barcelona → Paris", color = Color.Gray, fontSize = 14.sp)
        }
    }
}