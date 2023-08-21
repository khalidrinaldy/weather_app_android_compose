package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun WeatherForecast(
    state: WeatherState, modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { weatherData ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Today",
                style = TextStyle(fontWeight = FontWeight.Medium, color = Color.White)
            )
            LazyRow(content = {
                items(weatherData) { data ->
                    WeatherHourForecast(
                        time = data.time,
                        weatherType = data.weatherType,
                        temperatureCelcius = data.temperatureCelsius,
                        modifier = Modifier.height(120.dp)
                    )
                }
            })
        }
    }
}