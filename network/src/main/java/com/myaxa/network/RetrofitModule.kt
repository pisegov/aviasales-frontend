package com.myaxa.network

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import jakarta.inject.Inject
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class RetrofitModule @Inject constructor(
    baseUrl: String,
) {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(CallInterceptor())
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    private val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}