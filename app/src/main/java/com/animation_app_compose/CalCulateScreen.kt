package com.animation_app_compose

import android.graphics.Path.Op
import androidx.annotation.DrawableRes
import androidx.annotation.Size
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.animation_app_compose.states.DashboardState
import com.animation_app_compose.ui.theme.dark
import com.animation_app_compose.ui.theme.greyTextColor
import com.animation_app_compose.ui.theme.orangeColor
import kotlinx.coroutines.delay

@Composable
fun CalculateScreen(dashboardState: DashboardState) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ) {
        Text(
            text = "Destination",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W500)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Animation(delay = 100) {
            Card(
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            ) {
                Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)) {
                    Animation(delay = 100) {
                        TextInput(hint = "Sender location", leadingIcon = R.drawable.out)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Animation(delay = 250) { TextInput(hint = "Receiver location") }
                    Spacer(modifier = Modifier.height(10.dp))
                    Animation(delay = 400) { TextInput(hint = "Approx weight") }
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Packaging",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W500),
            modifier = Modifier.padding(bottom = 3.dp)
        )
        Text(
            text = "What are you sending?",
            style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.W400, color = greyTextColor)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Animation(delay = 200) {
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(55.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.parcel),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(50.dp)
                    )
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(height = 30.dp, width = 0.7.dp)
                            .background(color = Color(0xFFAAAAAA))
                    )
                    Text(
                        text = "Box",
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W500)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = greyTextColor,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Category",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W500),
            modifier = Modifier.padding(bottom = 3.dp)
        )
        Text(
            text = "What are you sending?",
            style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.W400, color = greyTextColor)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OptionGroup()


        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                dashboardState.showRoute(Routes.Estimate)
            },
            colors = ButtonDefaults.buttonColors(containerColor = orangeColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(text = "Calculate")
        }
        Spacer(modifier = Modifier.height(140.dp))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TextInput(@DrawableRes leadingIcon: Int = R.drawable.out, hint: String = "Hint") {
    val textValue = remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()


    BasicTextField(
        value = textValue.value,
        interactionSource = interactionSource,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        cursorBrush = SolidColor(Color(0xFF292929)),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = Color(0xFFEEEEEE), shape = RoundedCornerShape(10.dp))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Image(
                        painter = painterResource(leadingIcon),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(25.dp)
                    )
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(height = 30.dp, width = 0.7.dp)
                            .background(color = Color(0xFFAAAAAA))
                    )
                    if (textValue.value.isEmpty() && !isFocused) {
                        Text(
                            text = hint,
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.W400
                            )
                        )
                    }else {
                        innerTextField()
                    }
                }
            }
        },
        onValueChange = {
            textValue.value = it
        },
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle(
            color = Color(0xFF292929),
            fontWeight = FontWeight.W400,
            fontSize = 16.sp
        ),
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun OptionGroup() {
    val options = listOf("Documents", "Glass", "Liquid", "Food", "Electronic", "Product", "Others")
    val selected = remember {
        mutableStateOf("")
    }
    FlowRow() {
        repeat(options.size) {
            Animation(delay = (100 * it).toLong(), Direction.Horizontal, duration = 1200) {
            OptionItem(label = options[it], isSelected = selected.value.equals(options[it], ignoreCase = true)){ value ->
                selected.value = value
            }
            }
        }
    }
}


@Composable
fun OptionItem(label: String, isSelected: Boolean, onClick:(String) -> Unit) {

    val animatedColor by animateColorAsState(
        targetValue = if (isSelected) dark else Color.White,
        label = "color",animationSpec = tween(durationMillis = 800,  easing = CustomEasing)
    )

    val animatedTextColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else dark,
        label = "color", animationSpec = tween(durationMillis = 800,  easing = CustomEasing)
    )

    val withInDp = animateDpAsState(
        targetValue = if (isSelected) with(LocalDensity.current){25.dp} else 0.dp,
        animationSpec = tween(
            durationMillis = 400, easing = CustomEasing
        ), label = "width"
    )

    val heightInDp = animateDpAsState(
        targetValue = if (isSelected) with(LocalDensity.current){15.dp} else 0.dp,
        animationSpec = tween(
            durationMillis = 400,  easing = CustomEasing
        ), label = "height"
    )


    Box(
        modifier = Modifier
            .padding(end = 10.dp, bottom = 10.dp)
            .background(color = animatedColor, shape = RoundedCornerShape(8.dp))
            .clickable {
                onClick(label)
            }
            .border(width = 0.4.dp, color = animatedTextColor, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AnimatedVisibility(
                modifier = Modifier.size(width = withInDp.value, height = heightInDp.value),
                visible = isSelected,
                enter = fadeIn(animationSpec = tween(delayMillis = 200, durationMillis = 600, easing = CustomEasing)),
                exit = fadeOut(animationSpec = tween(delayMillis = 200, durationMillis = 600, easing = CustomEasing), )
            ) {

                Icon(Icons.Default.Done, contentDescription = "", tint = animatedTextColor, modifier = Modifier
                    .padding(start = 3.dp, end = 8.dp)
                    .size(15.dp))
            }

            Text(text = label, style = TextStyle(fontSize = 15.sp, color = animatedTextColor))
        }
    }
}
