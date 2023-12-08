package com.vijay.quotegenerator

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://zenquotes.io/api/"

    private fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val quoteApi : QuotesApi = getInstance().create(QuotesApi::class.java)

}