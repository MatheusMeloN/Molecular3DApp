package br.com.unir.app.apisearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSearch {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://search.rcsb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SearchService by lazy {
        retrofit.create(SearchService::class.java)
    }
}

