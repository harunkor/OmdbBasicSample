package com.harunkor.omdbbasicsample.viewmodel.search



import androidx.lifecycle.ViewModel
import com.harunkor.omdbbasicsample.model.Search.SearchResponse
import com.harunkor.omdbbasicsample.service.ApiLoginClient
import com.harunkor.omdbbasicsample.service.ApiRetroInterface
import com.harunkor.omdbbasicsample.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.harunkor.omdbbasicsample.utils.RetrofitSearchCallBack


//https://www.omdbapi.com/?s=agent&page=1&apikey=ed0bb07e

class SearchViewModel : ViewModel() {

    private lateinit var apiRetroInterface:ApiRetroInterface
    private val apikey:String= Constants.API_KEY

    fun init(s:String, page:String, retrofitSearchCallBack: RetrofitSearchCallBack){
        loadSearchMovies(s,page,retrofitSearchCallBack)
    }

    fun loadSearchMovies(s:String, page:String, retrofitSearchCallBack: RetrofitSearchCallBack)  {
            apiRetroInterface = ApiLoginClient().getLogin()!!.create(ApiRetroInterface::class.java)
            val call: Call<SearchResponse> =apiRetroInterface.SearchMovies(s,page,apikey)
            call.enqueue(object : Callback<SearchResponse?> {
                override fun onResponse(call: Call<SearchResponse?>?, response: Response<SearchResponse?>) {

                    if (response.body() != null){
                        retrofitSearchCallBack.onSucess(response.body()!!)
                    }
                }
                override fun onFailure(call: Call<SearchResponse?>?, t: Throwable?) {
                    retrofitSearchCallBack.onFailure()

                }
            })


    }

}