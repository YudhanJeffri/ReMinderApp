package com.reminder.reminderapp.client

import com.google.gson.GsonBuilder
import com.reminder.reminderapp.data.constant.Constant.BaseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getRetrofitInstance(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient =  OkHttpClient.Builder().build()
        return Retrofit.Builder()
                .baseUrl(BaseUrl)
            .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
    }
}