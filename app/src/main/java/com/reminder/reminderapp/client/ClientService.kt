package com.reminder.reminderapp.client

import com.reminder.reminderapp.data.model.mapel.MapelResponse
import retrofit2.Call
import retrofit2.http.GET

interface ClientService {
    @GET("api/mapels")
    fun mapels(): Call<MapelResponse>
}