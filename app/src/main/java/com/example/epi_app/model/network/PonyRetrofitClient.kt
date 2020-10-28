package com.example.epi_app.model.network

import com.example.epi_app.model.netw.PonyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PonyRetrofitClient {
    companion object {
        private const val BASE_URL= "https://pixabay.com/"

        fun retrofitInstance(): PonyApi {
            val retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitClient.create(PonyApi::class.java)
        }
    }

}