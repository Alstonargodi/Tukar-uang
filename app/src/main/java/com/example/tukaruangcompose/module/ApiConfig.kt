package com.example.tukaruangcompose.module

import com.example.tukaruang2.model.service.CurrencyService
import com.example.tukaruangcompose.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private const val baseUrl = "http://api.currencylayer.com/"

    fun setCurrencyService(): CurrencyService{
        val loggingInterceptor =
            if (BuildConfig.DEBUG){
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            }else{
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.NONE
                )
            }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(CurrencyService::class.java)
    }
}