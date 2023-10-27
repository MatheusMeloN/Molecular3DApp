import br.com.unir.app.apidata.DataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitData {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://data.rcsb.org/rest/v1/core/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: DataService by lazy {
        retrofit.create(DataService::class.java)
    }
}
