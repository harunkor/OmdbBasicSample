package com.harunkor.omdbbasicsample.viewmodel.detail

import androidx.lifecycle.ViewModel
import com.harunkor.omdbbasicsample.model.Detail.Detail
import com.harunkor.omdbbasicsample.service.ApiLoginClient
import com.harunkor.omdbbasicsample.service.ApiRetroInterface
import com.harunkor.omdbbasicsample.utils.Constants
import com.harunkor.omdbbasicsample.utils.RetrofitDetailCallBack
import com.harunkor.omdbbasicsample.utils.RetrofitSearchCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    //https://www.omdbapi.com/?i=tt2679042&apikey=ed0bb07e&plot=full
    private lateinit var apiRetroInterface: ApiRetroInterface
    private val apikey:String= Constants.API_KEY

    fun int(i:String, plot:String, retrofitDetailCallBack: RetrofitDetailCallBack){
        loadDetailMovie(i,plot,retrofitDetailCallBack)
    }

    fun loadDetailMovie(i:String, plot:String, retrofitDetailCallBack: RetrofitDetailCallBack) {

            apiRetroInterface = ApiLoginClient().getLogin()!!.create(ApiRetroInterface::class.java)
            val call: Call<Detail> =apiRetroInterface.DetailMovie(i,apikey,plot)
            call.enqueue(object : Callback<Detail?> {
                override fun onResponse(call: Call<Detail?>?, response: Response<Detail?>) {
                    if (response.body() != null){
                        retrofitDetailCallBack.onSucess(response.body()!!)
                    }
                }
                override fun onFailure(call: Call<Detail?>?, t: Throwable?) {
                    retrofitDetailCallBack.onFailure()

                }
            })

    }

}