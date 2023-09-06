package br.com.unir.app.model

data class Query(
    val query_id: String,
    val result_set: List<ResultQuery>,
    val result_type: String,
    val total_count: Int
)