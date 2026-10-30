package com.example.maps_app.network.services

import com.example.maps_app.model.network.CountryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountriesService {
    @GET("/countries/v5")
    suspend fun obtenerPaises(@Query("region") region: String): CountryResponse

    @GET("/countries/v5/codes.alpha_2/{codigo}")
    suspend fun obtenerPais(@Path("codigo") codigo: String): CountryResponse
}