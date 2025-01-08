package com.viclourenco.ticketscanner.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "http://10.0.2.2:8000/api/"

private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface TicketsApiService {
    @GET("tickets/{ticketCode}")
    suspend fun getPurchase(@Path("ticketCode") ticketCode: String): PurchaseResponse

    @GET("tickets/{ticketCode}/check-in")
    suspend fun checkIn(@Path("ticketCode") ticketCode: String): CheckInResponse
}

object TicketsApi {
    val retrofitService : TicketsApiService by lazy {
        retrofit.create(TicketsApiService::class.java)
    }
}