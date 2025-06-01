package com.example.wetherforecast.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.wetherforecast.R
import com.example.wetherforecast.model.Item0
import com.example.wetherforecast.model.Weather
import com.example.wetherforecast.util.formatDate
import com.example.wetherforecast.util.formatDateTime
import com.example.wetherforecast.util.formatDecimals


@Composable
fun SunRiseSunSetRow(weather: Weather) {
    Row(modifier = Modifier.padding(12.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise",
                modifier = Modifier.size(25.dp))
            Text(text = formatDateTime(weather.list[0].sunrise),
                style = MaterialTheme.typography.bodyMedium)
        }
        Row(modifier = Modifier.padding(4.dp)) {

            Text(text = formatDateTime(weather.list[0].sunset),
                style = MaterialTheme.typography.bodyMedium)
            Icon(painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset",
                modifier = Modifier.size(25.dp))
        }
    }
}

@Composable
fun HumidityWindPressureRow(weather: Weather) {
    Row(modifier = Modifier.padding(12.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.list[0].humidity}%",
                style = MaterialTheme.typography.bodyMedium)
        }
        Row(modifier = Modifier.padding(2.dp)) {
            Icon(painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.list[0].pressure} pre",
                style = MaterialTheme.typography.bodyMedium)
        }
        Row(modifier = Modifier.padding(2.dp)) {
            Icon(painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind",
                modifier = Modifier.size(20.dp))
            Text(text = " ${weather.list[0].speed} m/s",
                style = MaterialTheme.typography.bodyMedium)
        }

    }
}

@Composable
fun Content(weather : Weather){

    val imageUrl = "https://openweathermap.org/img/wn/${weather.list[0].weather[0].icon}.png"
    Surface(modifier = Modifier.size(200.dp),
        shape = CircleShape,
        color = Color(0xFFFFC400)

    ) {

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            weatherImageState(imageUrl)
            Text(text = formatDecimals(weather.list[0].temp.day) + "°",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold)
            Text(text = weather.list[0].weather[0].main,
                fontStyle = FontStyle.Italic)
        }
    }
}


@Composable
fun weatherImageState(imageUrl : String){
    Image(painter = rememberImagePainter( imageUrl), contentDescription = "weather",
        modifier = Modifier.size(80.dp))
}


@Composable
fun WeakDataRow(weather: Weather) {
    Surface(modifier = Modifier.fillMaxWidth()
        .fillMaxHeight()
        .padding(4.dp),
        color = Color(0xFFE0E0E0),
        shape = RoundedCornerShape(14.dp),

        ) {
        LazyColumn(modifier = Modifier.padding(4.dp),
            contentPadding = PaddingValues(1.dp),
        ) {
            items(7) { item ->
                WeatherDetailRow(weather.list[item],0)
            }
        }
    }
}
@Composable
fun MonthDataRow(weather: Weather) {
    Surface(modifier = Modifier.fillMaxWidth()
        .fillMaxHeight()
        .padding(4.dp),
        color = Color(0xFFE0E0E0),
        shape = RoundedCornerShape(14.dp),

        ) {
        LazyColumn(modifier = Modifier.padding(4.dp),
            contentPadding = PaddingValues(1.dp),
        ) {
            items(weather.list.size) { item ->
                WeatherDetailRow(weather.list[item],1)
            }
        }
    }
}
@Composable
fun WeatherDetailRow(item: Item0,i :Int){
    Surface(modifier = Modifier.fillMaxWidth()
        .padding(5.dp),
        shape = RoundedCornerShape(topEnd = 8.dp, bottomStart = 20.dp),
        color = Color.White
    ) {
        val imageUrl = "https://openweathermap.org/img/wn/${item.weather[0].icon}.png"
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formatDate(item.dt).split(",")[i],
                modifier = Modifier.padding(5.dp))
            weatherImageState(imageUrl)
            Surface(
                shape = CircleShape,
                color = Color(0xffffc400)
            ) { Text(text = item.weather[0].description,
                style = MaterialTheme.typography.bodyMedium) }
            Row {
                Text(text = item.temp.max.toString()+"° ",
                    color = Color(0xFF2196F3),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge)
                Text(text = item.temp.min.toString()+"°",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun Datadetails(weather: Weather){
    val weakState = remember { mutableStateOf(false) }
    val monthState = remember { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth()
        .border(width = 2.dp,
            color = Color(0xFF362B2B),
            shape = RoundedCornerShape(20.dp)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.92f),
            horizontalArrangement = Arrangement.Center // Centers Text in the Row
        ) {
            Text(
                text = "This Weak",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(6.dp)

            ) }
        //
        Icon(imageVector =if (!weakState.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp, contentDescription = "More",
            modifier = Modifier
                .size(20.dp)
                .clickable(onClick = { weakState.value = !weakState.value }))

    }


    if (weakState.value) {
        WeakDataRow(weather)
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier.fillMaxWidth()
        .border(width = 2.dp,
            color = Color(0xFF362B2B),
            shape = RoundedCornerShape(20.dp)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.92f),
            horizontalArrangement = Arrangement.Center // Centers Text in the Row
        ) {
            Text(
                text = "This Month",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(6.dp)

            ) }
        //
        Icon(imageVector =if (!monthState.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp, contentDescription = "More",
            modifier = Modifier
                .size(20.dp)
                .clickable(onClick = { monthState.value = !monthState.value }))

    }


    if (monthState.value) {
        MonthDataRow(weather)
    }
}