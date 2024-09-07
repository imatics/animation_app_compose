package com.animation_app_compose

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.animation_app_compose.states.DashboardState
import com.animation_app_compose.states.rememberDashboardState
import com.animation_app_compose.ui.theme.appColor
import com.animation_app_compose.ui.theme.greyTextColor
import com.animation_app_compose.ui.theme.orangeColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun Dashboard(dashboardState: DashboardState) {


    BackHandler(dashboardState.tabSelected != 0) {
       dashboardState.selectTab(0)
    }
    Scaffold(
        Modifier.background(color = Color(0xFFAAAAAA)),
        bottomBar = {
            BottomBar(dashboardState)
        }
    ) {
        Column(
            Modifier
                .background(color = Color(0xFFEEEEEE))
                .fillMaxHeight(),) {
            Toolbar(dashboardState)
            AnimatedContent(
                targetState = dashboardState.tabSelected,
                transitionSpec = {

                    slideInVertically(
                        animationSpec = tween(600, easing = CustomEasing),
                        initialOffsetY = { fullWidth -> (fullWidth * 0.1).toInt() }
                    ).plus(fadeIn(tween(durationMillis = 600))) with
                            fadeOut(
                                animationSpec = tween(100, easing = CustomEasing),
                            )

                }, label = ""
            ) {
                if(dashboardState.shouldShowSearch){
                    SearchScreen()
                } else {
                    when (it) {
                        0 -> Home()
                        1 -> CalculateScreen(dashboardState)
                        2 -> ShipmentScreen(dashboardState)
                        3 -> Box {}
                    }
                    Box {}
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview
fun Toolbar(dashboardState: DashboardState = rememberDashboardState()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = appColor)
        ) {
            Column(modifier = Modifier.padding(horizontal = 10.dp)){
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                AnimatedVisibility(visible = dashboardState.shouldShowSearch) {
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                }
                AnimatedVisibility(visible = dashboardState.shouldShowTopBar) {
                    Column {
                        Spacer(
                            modifier = Modifier.height(40.dp)
                        )
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(width = 50.dp, height = 50.dp)
                                    .background(color = Color.Cyan, shape = CircleShape)
                            )
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(horizontal = 15.dp)
                            ) {
                                Row {
                                    Icon(
                                        Icons.Default.Place,
                                        contentDescription = "",
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = "Your Location",
                                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                                    )
                                }
                                Text(
                                    text = "Wertheimer, Illinois",
                                    style = TextStyle(color = Color.White, fontSize = 18.sp)
                                )

                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .size(width = 50.dp, height = 50.dp)
                                    .background(color = Color.White, shape = CircleShape)
                            ) {
                                Icon(
                                    Icons.Outlined.Notifications,
                                    contentDescription = "",
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    )
                                )

                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    AnimatedVisibility(visible = dashboardState.shouldShowBack) {
                        IconButton(onClick = {
                            dashboardState.selectTab(0)
                        })
                        {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "cancel Nav", tint = Color.White
                            )
                        }
                    }

                    AnimatedContent(
                        targetState =  dashboardState.shouldShowTitle.value && dashboardState.titleValue.isNotEmpty(),
                        transitionSpec = {
                            fadeIn(
                                animationSpec = tween(500, easing = CustomEasing)
                            ) with fadeOut(
                                animationSpec = tween(500, easing = CustomEasing),
                            )

                        }, label = ""
                    ) {
                        if(it){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = dashboardState.titleValue,
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 18.sp,
                                            FontWeight.W500
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.width(45.dp))
                            }
                        }else{

                            SearchBar(dashboardState){
                                dashboardState.showSearch(it)
                            }
                        }
                    }

                }
            }


            AnimatedContent(
                targetState = dashboardState.shouldShowTab,
                transitionSpec = {
                    fadeIn(
                        animationSpec = tween(500, easing = CustomEasing)
                    ) with fadeOut(
                        animationSpec = tween(500, easing = CustomEasing),
                    )

                }, label = ""
            ) {

                if(it){
                    Animation(delay = 200, Direction.Horizontal) {
                        ScrollableTabRow(
                            modifier = Modifier.padding(top = 0.dp),
                            containerColor = Color.Transparent,
                            edgePadding = 5.dp,
                            indicator = { tabPositions ->
                                if (dashboardState.shipmentSelected < tabPositions.size) {
                                    TabRowDefaults.Indicator(
                                        modifier = Modifier.tabIndicatorOffset(tabPositions[dashboardState.shipmentSelected]),
                                        color = orangeColor
                                    )
                                }
                            },
                            selectedTabIndex = dashboardState.shipmentSelected
                        ) {
                            Tabs.entries.forEachIndexed { index, title ->
                                Tab(
                                    text = {
                                        Row(modifier = Modifier.height(30.dp)) {
                                            Text(
                                                title.label,
                                                style = TextStyle(fontWeight = FontWeight.W500)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Row(
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier
                                                    .background(
                                                        color = if (dashboardState.shipmentSelected == index) orangeColor else Color(
                                                            0xABFFFFFF
                                                        ), shape = RoundedCornerShape(10.dp)
                                                    )
                                                    .height(20.dp)
                                                    .padding(horizontal = 9.dp, vertical = 0.dp)
                                            ) {
                                                Text(
                                                    text = "${title.count}",
                                                    style = TextStyle(color = Color.White)
                                                )
                                            }
                                        }
                                    },
                                    selected = dashboardState.shipmentSelected == index,
                                    onClick = { dashboardState.selectShipmentTab(index) },
                                    unselectedContentColor = Color(0xBAFFFFFF),
                                    selectedContentColor = Color.White,

                                    )
                            }
                        }
                    }
                }else{
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

        }
}


@Composable
@Preview
fun TrackingCard() {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        ),
        modifier = Modifier
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Spacer(modifier = Modifier.height(height = 10.dp))
            Row {
                Column {
                    Text(text = "Shipment", style = TextStyle(color = Color.Gray), fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "NEJ20089934122231",
                        style = TextStyle(color = Color.Black),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(id = R.drawable.forklift), contentDescription = "", modifier = Modifier.size(60.dp))
            }
            Box(
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(color = Color(0xFFDDDDDD))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
                Image(painter = painterResource(id = R.drawable.send_imge), contentDescription = "", modifier = Modifier.size(45.dp))
                Spacer(modifier = Modifier.width(20.dp))
                LabelValuePair(label = "Sender", value = "Altanta, 5243")
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier.weight(2f)) {
                    LabelValuePair(label = "Time", value = "2 days - 3 days", showStatus = true)
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
                Image(painter = painterResource(id = R.drawable.send_imge), contentDescription = "", modifier = Modifier.size(45.dp))
                Spacer(modifier = Modifier.width(20.dp))
                LabelValuePair(label = "Receiver", value = "Chicago, 5243")
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier.weight(2f)) {
                    LabelValuePair(label = "Status", value = "Waiting to collect")
                }

            }
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(color = Color(0xFFDDDDDD))
                    .padding(horizontal = 10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = orangeColor
                    ),
                    modifier = Modifier.width(250.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "")
                        Text(text = "Add Stop")
                    }
                }
            }


        }
    }
}


@Preview()
@Composable
fun LabelValuePair(label: String = "hello", value: String = "world", showStatus: Boolean = false) {
    Column {
        Text(
            label,
            style = TextStyle(
                fontWeight = FontWeight.W400, color = Color.Gray, fontSize = 12.sp
            )
        )
        Spacer(modifier = Modifier.height(height = 2.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (showStatus)
                Box(
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(6.dp)
                        .background(color = Color.Green, shape = CircleShape)
                )
            Text(
                value,
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    color = Color(0XFF111111),
                    fontSize = 14.sp
                ),
            )
        }
    }
}


@Composable
fun SearchBar(dashboardState: DashboardState, onFocusChanged:(Boolean) -> Unit) {
    val  textValue = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()


    LaunchedEffect(dashboardState.shouldShowSearch) {
        if(!dashboardState.shouldShowSearch){
          focusManager.clearFocus()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = Color.White, shape = RoundedCornerShape(30.dp))
    ) {

        BasicTextField(
            value = textValue.value,
            cursorBrush = SolidColor(Color(0xFF292929)),
            interactionSource = interactionSource,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .onFocusChanged {
                    onFocusChanged(it.hasFocus)
                },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(30.dp))
                ) {
                    Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()){

                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(Icons.Default.Search, contentDescription = "", modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(25.dp))
                        Box (modifier = Modifier.weight(1f)){
                            if (textValue.value.isEmpty() && !isFocused) {
                            Text(text = "Enter the receipt number", style = TextStyle(fontSize = 16.sp, color = Color.Gray, fontWeight = FontWeight.W400))
                        }else {

                                innerTextField()
                            }
                        }

                        IconButton(
                            onClick = {  },
                            Modifier.background(color = Color(0xFFFF6F00), shape = CircleShape)
                        ) {
                            Icon(
                                Icons.Filled.AccountBox,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .size(30.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }

                }
            },
            onValueChange = {
                textValue.value = it
            },
            singleLine = true,

            maxLines = 1,
            textStyle = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp
            ),
        )

    }
}


@Preview
@Composable
fun VehicleCard(
    title: String = "Ship",
    subTitle: String = "International",
    @DrawableRes image: Int = R.drawable.truck,
    index: Int = 0
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.padding(end = 15.dp)
    ) {
        Box {
            Column(
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    title,
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF888888)
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    subTitle,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF888888)
                    ),
                )
            }

        }

        Animation(delay = (index + 1) * 150L, Direction.Horizontal) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier.size(width = 140.dp, height = 140.dp)
            )
        }

    }
}


@Composable
fun BottomBar(dashboardState: DashboardState) {
    val scope = rememberCoroutineScope()
    val items = listOf<MenuItem>(
        MenuItem(label = "Home", image = Icons.Outlined.Home),
        MenuItem(label = "Calculate", image = Icons.Outlined.List),
        MenuItem(label = "Shipment", image = Icons.Outlined.ShoppingCart),
        MenuItem(label = "Profile", image = Icons.Outlined.Person),
    )

    val width = remember { mutableIntStateOf(0) }
    val oldSelectedIndex = remember { mutableIntStateOf(0) }
    val offSet = remember { androidx.compose.animation.core.Animatable(0f) }
    val colors = items.map { remember { Animatable(greyTextColor) } }

    LaunchedEffect(dashboardState.tabSelected) {
        scope.launch {
            offSet.animateTo(
                targetValue = dashboardState.tabSelected.times(width.intValue / items.size)
                    .toFloat(),
                animationSpec = tween(durationMillis = 800, delayMillis = 0)
            )

            oldSelectedIndex.intValue = dashboardState.tabSelected
        }
        launch {
            colors[dashboardState.tabSelected].animateTo(appColor, animationSpec = tween(500))
        }
        launch {
            colors[oldSelectedIndex.intValue].animateTo(greyTextColor, animationSpec = tween(500))
        }
        delay(timeMillis = 100)
        oldSelectedIndex.intValue = dashboardState.tabSelected
    }

    AnimatedVisibility(
        visible = dashboardState.shouldShowBottomBar,
        enter =  slideInVertically(
            animationSpec = tween(600, easing = CustomEasing, delayMillis = 200),
            initialOffsetY = { fullWidth -> (fullWidth * 0.9).toInt() }
        ).plus(fadeIn(tween(durationMillis = 500, delayMillis = 100))),
        exit = slideOutVertically(
            animationSpec = tween(900, easing = CustomEasing, delayMillis = 600),
            targetOffsetY = { fullWidth -> (fullWidth * 0.9).toInt() }
        ).plus(fadeOut(tween(durationMillis = 900, delayMillis = 600))),
    ) {

    Box(modifier = Modifier
        .background(color = Color.White)
        .fillMaxWidth()
        .height(85.dp)
        .onGloballyPositioned {
            width.intValue = it.size.width
        }) {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(color = Color(0xFFE0E0E0))

                )
                Box(modifier = Modifier
                    .offset {
                        IntOffset(x = offSet.value.toInt(), 0)
                    }
                    .height(5.dp)
                    .width((width.intValue / items.size).pxToDp())
                    .background(color = appColor)

                )
            }
        }
        Row() {
            repeat(items.size) {
                Column(
                    modifier = Modifier
                        .clickable {
                            dashboardState.selectTab(it, items[it])
                        }
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(items[it].image, contentDescription = "", tint = colors[it].value, modifier = Modifier.size(25.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = items[it].label,
                        style = TextStyle(
                            color = colors[it].value,
                            fontSize = 14.sp,
                            FontWeight.W500
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
}


data class MenuItem(val label: String, val image: ImageVector)

enum class Tabs(val label:String, val count:Int){
    All("All", 9),
    Completed("Completed", 3),
    InProgress("In Progress", 2),
    Pending("Pending", 4),
}





