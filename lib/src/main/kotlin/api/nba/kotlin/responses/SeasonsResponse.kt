package api.nba.kotlin.responses

import kotlinx.serialization.Serializable

@Serializable
data class SeasonsResponse(
    val get: String,
    val parameters: List<String>,
    val errors: List<String>,
    val results: Int,
    val response: List<Int>,
)
