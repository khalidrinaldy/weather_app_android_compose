package com.plcoding.weatherapp.data.mappers

import com.plcoding.weatherapp.data.remote.WeatherDataDto
import com.plcoding.weatherapp.data.remote.WeatherDto
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData (
    val index : Int,
    val data : WeatherData
        )
fun WeatherDataDto.toWeatherDataMap() : Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures.elementAt(index)
        val weatherCode = weatherCodes.elementAt(index)
        val windSpeed = windSpeeds.elementAt(index)
        val pressure = pressures.elementAt(index)
        val humidity = humidities.elementAt(index)
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    } .mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo() : WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap();
    val now = LocalDateTime.now();
    val currentWeather = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour+1
        hour == it.time.hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeather,
    );
}