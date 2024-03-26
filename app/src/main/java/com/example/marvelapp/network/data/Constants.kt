package com.example.marvelapp.network.data

import com.example.marvelapp.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest

class Constants {

    companion object {
        val timeStamp = System.currentTimeMillis().toString()
        const val BASE_URL = BuildConfig.MARVEL_BASE_URL
        private const val PRIVATE_KEY = BuildConfig.MARVEL_PRIVATE_KEY
        const val API_KEY = BuildConfig.MARVEL_PUBLIC_KEY
        const val limit = 3
        fun hash(): String {
            val input = timeStamp + PRIVATE_KEY + API_KEY
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}
