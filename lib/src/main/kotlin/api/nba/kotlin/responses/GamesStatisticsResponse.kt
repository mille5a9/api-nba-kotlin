package api.nba.kotlin.responses

import api.nba.kotlin.models.GameStatistics
import api.nba.kotlin.models.Team
import kotlinx.serialization.Serializable

@Serializable
data class GamesStatisticsResponse(
    val get: String,
    val parameters: Map<String, String>,
    val errors: List<String>,
    val results: Int,
    val response: List<TeamStats>,
) {
    @Serializable
    data class TeamStats(
        val team: Team,
        val statistics: List<GameStatistics>,
    )
}
