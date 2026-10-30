package com.example.maps_app.network
import com.example.maps_app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthInterceptorModule {
    val tokenCountries = BuildConfig.KEY_COUNTRIES

    @Provides
    @Singleton
    fun provideAuthClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenCountries))
            .connectTimeout(2, TimeUnit.SECONDS)
            .build()
    }
}