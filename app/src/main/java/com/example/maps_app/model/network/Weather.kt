package com.example.maps_app.model.network

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val location: Location? = null,
    val current: CurrentWeather? = null
)

@Serializable
data class Location(
    val name: String? = null,
    val region: String? = null,
    val country: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val tz_id: String? = null,
    val localtime_epoch: Long? = null,
    val localtime: String? = null
)

@Serializable
data class CurrentWeather(
    val last_updated_epoch: Long? = null,
    val last_updated: String? = null,
    val temp_c: Double? = null,
    val temp_f: Double? = null,
    val is_day: Int? = null,
    val condition: Condition? = null,
    val wind_mph: Double? = null,
    val wind_kph: Double? = null,
    val wind_degree: Int? = null,
    val wind_dir: String? = null,
    val pressure_mb: Double? = null,
    val pressure_in: Double? = null,
    val precip_mm: Double? = null,
    val precip_in: Double? = null,
    val humidity: Int? = null,
    val cloud: Int? = null,
    val feelslike_c: Double? = null,
    val feelslike_f: Double? = null,
    val windchill_c: Double? = null,
    val windchill_f: Double? = null,
    val heatindex_c: Double? = null,
    val heatindex_f: Double? = null,
    val dewpoint_c: Double? = null,
    val dewpoint_f: Double? = null,
    val vis_km: Double? = null,
    val vis_miles: Double? = null,
    val uv: Double? = null,
    val gust_mph: Double? = null,
    val gust_kph: Double? = null,
    val will_it_rain: Int? = null,
    val chance_of_rain: Int? = null,
    val will_it_snow: Int? = null,
    val chance_of_snow: Int? = null,
    val wetbulb_c: Double? = null,
    val wetbulb_f: Double? = null
)

@Serializable
data class Condition(
    val text: String? = null,
    val icon: String? = null,
    val code: Int? = null
)