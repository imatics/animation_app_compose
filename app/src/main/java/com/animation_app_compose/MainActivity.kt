package com.animation_app_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.animation_app_compose.states.rememberDashboardState
import com.animation_app_compose.ui.theme.Animation_app_composeTheme

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            val navController = rememberNavController()
            val dashboardState = rememberDashboardState()

            LaunchedEffect(dashboardState.routeValue) {
                navController.navigate(dashboardState.routeValue.name)
            }

            Animation_app_composeTheme {
                NavHost(navController = navController, startDestination = Routes.Start.name){
                    composable(Routes.Start.name){ Dashboard(dashboardState) }
                    composable(Routes.Estimate.name){ Estimate(dashboardState) }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Animation_app_composeTheme {
        Greeting("Android")
    }
}


enum class Routes{
    Start, Estimate
}