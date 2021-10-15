package com.harunkor.omdbbasicsample.utils

import com.harunkor.omdbbasicsample.model.Search.SearchResponse
import java.util.*

interface RetrofitSearchCallBack  {
    fun onSucess(searchResponse: SearchResponse)
    fun onFailure()
}