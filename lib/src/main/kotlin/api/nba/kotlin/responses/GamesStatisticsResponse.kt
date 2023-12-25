package api.nba.kotlin.responses

import api.nba.kotlin.models.GameStatistics
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

    @Serializable
    data class Team(
        val id: Int,
        val name: String,
        val nickname: String,
        val code: String,
        val logo: String,
    )
}
