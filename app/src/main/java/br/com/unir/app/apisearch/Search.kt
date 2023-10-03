package br.com.unir.app.apisearch

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.unir.app.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class Search : AppCompatActivity() {

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var editTextSearch: EditText
    private lateinit var buttonSearch: Button
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        editTextSearch = findViewById(R.id.editTextSearch)
        buttonSearch = findViewById(R.id.buttonSearch)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonSearch.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        val searchValue = editTextSearch.text.toString()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<ApiResponse> =
                    RetrofitInstance.api.fetchData(
                        """{
                            "query": {
                                "type": "terminal",
                                "service": "full_text",
                                "parameters": {
                                    "value": "$searchValue"
                                }
                            },
                            "return_type": "entry"
                        }"""
                    )

                if (response.isSuccessful) {
                    val data = response.body()?.result_set ?: emptyList()
                    withContext(Dispatchers.Main) {
                        adapter.updateData(data) // Atualiza o adaptador existente com os novos dados
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}