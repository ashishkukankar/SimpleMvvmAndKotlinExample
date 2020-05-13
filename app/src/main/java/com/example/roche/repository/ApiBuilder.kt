package com.example.roche.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
val BASE_URL = "http://192.168.2.21:8081/api/v1/user-management/"
class ApiBuilder {
    companion object {
        fun invoke(): ApiService? {

            try {
                val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                return retrofit.create(ApiService::class.java)
            } catch (e: Exception) {
                println("error message=${e.message}")
            }

            return null

        }
    }

}