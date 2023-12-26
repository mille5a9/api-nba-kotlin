package api.nba.kotlin.responses

import api.nba.kotlin.models.GameStatistics
import api.nba.kotlin.models.Team
import kotlinx.serialization.Serializable

@Serializable
data class GamesStatisticsResponse(
    val team: Team,
    val statistics: List<GameStatistics>,
)
