package com.harunkor.omdbbasicsample.service

import retrofit2.Retrofit
import okhttp3.OkHttpClient

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory


class ApiLoginClient {

    private var retrofit: Retrofit? = null

    fun getLogin(): Retrofit? {
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }
}