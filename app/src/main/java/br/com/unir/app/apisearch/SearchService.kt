package br.com.unir.app.apisearch

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("rcsbsearch/v2/query")
    suspend fun fetchData(@Query("json") json: String): Response<ApiResponse>
}

