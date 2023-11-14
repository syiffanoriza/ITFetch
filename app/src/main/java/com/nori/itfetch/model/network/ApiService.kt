package com.nori.itfetch.model.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/everything")
    fun getTechnologyNews(
        @Query("q") q: String = "tech",
        @Query("language") language: String = "en",
        @Query("pageSize") pageSize: Int = 50,
        @Query("sortBy") sortBy: String = "popularity"
    ): Call<NewsResponse>

    @GET("/v2/everything")
    fun getAiNews(
        @Query("q") q: String = "Artificial-Intelligence",
        @Query("language") language: String = "en"
    ): Call<NewsResponse>

    @GET("/v2/everything")
    fun getIotNews(
        @Query("q") q: String = "IOT",
        @Query("language") language: String = "en"
    ): Call<NewsResponse>

    @GET("/v2/everything")
    fun getBlockchainNews(
        @Query("q") q: String = "Blockchain",
        @Query("language") language: String = "en"
    ): Call<NewsResponse>

    @GET("/v2/everything")
    fun getSearchNews(
        @Query("q") q: String,
        @Query("pageSize") pageSize: Int= 19
    ): Call<NewsResponse>
}