package br.com.unir.app.apidata

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DataService {
    @GET("entry/{proteinId}")
    suspend fun getProteinData(@Path("proteinId") proteinId: String): Response<ProteinDataModel>
}
