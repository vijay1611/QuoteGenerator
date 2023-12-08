package com.vijay.quotegenerator

import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {

    @GET("random")
    suspend fun getRandomQuote():Response<List<QuoteModel>>

//    @GET("today")
//    suspend fun getTodayQuote():Response<List<QuoteModel>>
}