package com.example.maps_app.network
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestOriginal = chain.request()
        Log.d("jwt", token)
        val requestHeaderAuth = requestOriginal.newBuilder().header("Authorization", "Bearer $token").build()

        return chain.proceed(requestHeaderAuth)
    }
}