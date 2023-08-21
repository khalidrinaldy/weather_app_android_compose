package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun WeatherHourForecast(
    time: LocalDateTime,
    weatherType: WeatherType,
    temperatureCelcius: Double,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = time.format(DateTimeFormatter.ofPattern("HH:mm")),
            style = TextStyle(fontSize = 12.sp, color = Color.White)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Image(
            painter = painterResource(id = weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$temperatureCelcius°C",
            style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
        )
    }
}