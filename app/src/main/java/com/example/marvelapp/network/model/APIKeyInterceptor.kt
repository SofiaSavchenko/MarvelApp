package com.example.marvelapp.network.model

import com.example.marvelapp.network.data.Constants
import okhttp3.Interceptor
import okhttp3.Response

class APIKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val apikey = Constants.API_KEY
        val timestamp = Constants.TIMESTAMP
        val hash = Constants.HASH
        val currentUrl = chain.request().url
        val newUrl = currentUrl.newBuilder()
            .addQueryParameter("apikey", apikey)
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("hash", hash)
            .build()
        val currentRequest = chain.request().newBuilder()
        val newRequest = currentRequest.url(newUrl).build()
        return chain.proceed(newRequest)
    }
}