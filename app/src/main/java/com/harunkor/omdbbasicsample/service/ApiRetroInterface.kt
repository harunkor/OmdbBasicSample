package com.harunkor.omdbbasicsample.service

import com.harunkor.omdbbasicsample.model.Detail.Detail
import com.harunkor.omdbbasicsample.model.Search.Search
import com.harunkor.omdbbasicsample.model.Search.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRetroInterface {

    //https://www.omdbapi.com/?s=agent&page=1&apikey=ed0bb07e
    @GET("/")
    open fun SearchMovies(
        @Query("s") searchvalue: String,
        @Query("page") page: String,
        @Query("apikey") apikey: String
    ): Call<SearchResponse>

    //https://www.omdbapi.com/?i=tt2679042&apikey=ed0bb07e&plot=full
    @GET("/")
    open fun DetailMovie(
        @Query("i") imdbid: String,
        @Query("apikey") apikey: String,
        @Query("plot") plot: String  // 	short, full
    ): Call<Detail>

}