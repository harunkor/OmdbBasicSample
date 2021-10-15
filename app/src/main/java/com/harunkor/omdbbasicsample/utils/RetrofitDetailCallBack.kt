package com.harunkor.omdbbasicsample.utils

import com.harunkor.omdbbasicsample.model.Detail.Detail
import com.harunkor.omdbbasicsample.model.Search.SearchResponse
import java.util.*

interface RetrofitDetailCallBack  {
    fun onSucess(detail: Detail)
    fun onFailure()
}