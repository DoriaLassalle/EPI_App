package com.example.epi_app.model.netw


import com.example.epi_app.model.network.Pony
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PonyApi {

    @GET("api/")
    fun fetchAllPony(@QueryMap() pony: Map<String, String> ): Call<Pony> //LO QUE VA A DEVOLVER

}
