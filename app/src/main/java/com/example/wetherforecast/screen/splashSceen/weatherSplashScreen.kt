package com.example.wetherforecast.scre



import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wetherforecast.R
import com.example.wetherforecast.navigation.WeatherScreen
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController){
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }))

        delay(2000L)

        navController.navigate("${WeatherScreen.MainScreen.name}/indore")
    } )
    Surface(modifier = Modifier
        .padding(30.dp)
        .size(330.dp)
        .scale(scale.value),
        color = Color.White,
        shape = CircleShape,

        border = BorderStroke(1.dp, color = Color.LightGray)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.sun), contentDescription = "Sun")
            Text(text ="Find Sun ?",
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                style = MaterialTheme.typography.headlineMedium
                )
        }
    }
}