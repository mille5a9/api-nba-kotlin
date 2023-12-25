package api.nba.kotlin.responses

import api.nba.kotlin.models.TeamStatistics
import kotlinx.serialization.Serializable

@Serializable
data class TeamsStatisticsResponse(
    val get: String,
    val parameters: Map<String, String>,
    val errors: List<String>,
    val results: Int,
    val response: List<TeamStatistics>,
)
