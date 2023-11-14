package com.nori.itfetch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nori.itfetch.model.network.ApiClient
import com.nori.itfetch.model.network.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {
    private var _technologyNews = MutableLiveData<NewsResponse>()
    val technologyNews get() = _technologyNews as LiveData<NewsResponse>

    private var _AiNews = MutableLiveData<NewsResponse>()
    val AiNews get() = _AiNews as LiveData<NewsResponse>

    private var _IotNews = MutableLiveData<NewsResponse>()
    val IotNews get() = _IotNews as LiveData<NewsResponse>

    private var _BlockchainNews = MutableLiveData<NewsResponse>()
    val BlockchainNews get() = _BlockchainNews as LiveData<NewsResponse>

    private var _searchNews = MutableLiveData<NewsResponse>()
    val searchNews get() = _searchNews as LiveData<NewsResponse>

    fun technologyNews() {
        ApiClient.provideApiService().getTechnologyNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful){
                        Log.i(
                            "ViewModel",
                            "onResponse: Call success with HTTP status code ${response.body()}"
                        )
                        _technologyNews.postValue(response.body())
                    } else Log.e(
                        "ViewModel",
                        "onResponse: Call error with HTTP status code " + response.code()
                    )
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e(
                        "ViewModel",
                        "onFailure" + t.localizedMessage
                    )
                }
            })
    }

    fun aiNews() {
        ApiClient.provideApiService().getAiNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.i(
                            "ViewModel",
                            "onResponse: Call success with HTTP status code ${response.body()}"
                        )
                        _AiNews.postValue(response.body())
                    } else Log.e(
                        "ViewModel",
                        "onResponse: Call error with HTTP status code " + response.code()
                    )
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e(
                        "ViewModel",
                        "onFailure" + t.localizedMessage
                    )
                }
            })
    }

    fun iotNews() {
        ApiClient.provideApiService().getIotNews()
            .enqueue(object : Callback<NewsResponse>{
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>)
                {
                    if (response.isSuccessful) {
                        Log.i(
                            "ViewModel",
                            "${response.body()}"
                        )
                        _IotNews.postValue(response.body())
                    } else Log.e(
                        "ViewModel",
                        "onResponse: Call error with HTTP status code"
                    )
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e(
                        "ViewModel",
                        "onFailure: " + t.localizedMessage
                    )
                }
            })
    }

    fun blockchainNews() {
        ApiClient.provideApiService().getBlockchainNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.i(
                            "ViewModel",
                            "onResponse: ${response.body()}"
                        )
                        _BlockchainNews.postValue(response.body())
                    } else Log.e(
                        "ViewModel",
                        "onResponse: Call error with HTTP status code " + response.code()
                    )
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e(
                        "ViewModel",
                        "onFailure: " + t.localizedMessage
                    )
                }
            })
    }

    fun searchNews(q: String) {
        ApiClient.provideApiService().getSearchNews(q)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.i(
                            "ViewModel",
                            "onResponse: ${response.body()}"
                        )
                        _searchNews.postValue(response.body())
                    } else Log.e(
                        "ViewModel",
                        "onResponse: Call error with HTTP status code " + response.code()
                    )
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Log.e(
                        "ViewModel",
                        "onFailure: ${t.localizedMessage}"
                    )
                }
            })
    }
}
