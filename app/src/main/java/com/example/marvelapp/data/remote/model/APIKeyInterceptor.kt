package com.example.marvelapp.data.remote.model

import com.example.marvelapp.data.remote.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response

class APIKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val apikey = ApiConstants.API_KEY
        val timestamp = ApiConstants.TIMESTAMP
        val hash = ApiConstants.HASH
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