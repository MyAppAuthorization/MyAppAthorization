package com.example.myappathorization

import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {
    //@Headers("Content-Type: application/json")
    @POST("api/getToken")
    suspend fun auth(@Body authRequest: AuthRequest): User
}