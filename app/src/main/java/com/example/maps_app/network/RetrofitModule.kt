package com.example.maps_app.network

import com.example.maps_app.BuildConfig
import com.example.maps_app.network.services.CountriesService
import com.example.maps_app.network.services.GeoService
import com.example.maps_app.network.services.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Qualifier
    annotation class CountriesRetrofit

    @Qualifier
    annotation class GeoRetrofit

    @Qualifier
    annotation class WeatherRetrofit

    @CountriesRetrofit
    @Provides
    @Singleton
    fun provideCountriesRetrofit(authClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.restcountries.com/")
            .client(authClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @GeoRetrofit
    @Provides
    @Singleton
    fun provideGeoRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://secure.geonames.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @WeatherRetrofit
    @Provides
    @Singleton
    fun provideWeatherRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCountriesService(@CountriesRetrofit retrofit: Retrofit): CountriesService = retrofit.create(
        CountriesService::class.java)

    @Provides
    @Singleton
    fun provideGeoService(@GeoRetrofit retrofit: Retrofit): GeoService = retrofit.create(GeoService::class.java)

    @Provides
    @Singleton
    fun provideWeatherService(@WeatherRetrofit retrofit: Retrofit): WeatherService = retrofit.create(
        WeatherService::class.java)

}