package br.com.unir.app.apisearch

import br.com.unir.app.apisearch.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("rcsbsearch/v2/query")
    suspend fun fetchData(@Query("json") json: String): Response<ApiResponse>
}

