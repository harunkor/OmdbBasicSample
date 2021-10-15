package com.harunkor.omdbbasicsample.model.Search

data class SearchResponse (

    val Search : List<Search>,
    val totalResults : Int,
    val Response : Boolean

)