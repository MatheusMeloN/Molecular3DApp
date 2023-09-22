package br.com.unir.app.apisearch

data class ApiResponse(
    val query_id: String,
    val result_type: String,
    val total_count: Int,
    val result_set: List<ResultSetItem>
)

data class ResultSetItem(
    val identifier: String,
    val score: Double
)

