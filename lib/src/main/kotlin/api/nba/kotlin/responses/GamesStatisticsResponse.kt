package api.nba.kotlin.responses

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
        val statistics: List<Statistics>,
    )

    @Serializable
    data class Team(
        val id: Int,
        val name: String,
        val nickname: String,
        val code: String,
        val logo: String,
    )

    @Serializable
    data class Statistics(
        val fastBreakPoints: Int,
        val pointsInPaint: Int,
        val biggestLead: Int,
        val secondChancePoints: Int,
        val pointsOffTurnovers: Int,
        val longestRun: Int,
        val points: Int,
        val fgm: Int,
        val fga: Int,
        val fgp: String,
        val ftm: Int,
        val fta: Int,
        val ftp: String,
        val tpm: Int,
        val tpa: Int,
        val tpp: String,
        val offReb: Int,
        val defReb: Int,
        val totReb: Int,
        val assists: Int,
        val pFouls: Int,
        val steals: Int,
        val turnovers: Int,
        val blocks: Int,
        val plusMinus: String,
        val min: String,
    )
}
