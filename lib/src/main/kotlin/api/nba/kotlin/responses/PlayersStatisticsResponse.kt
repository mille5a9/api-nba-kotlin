package api.nba.kotlin.responses

import api.nba.kotlin.models.Team
import kotlinx.serialization.Serializable

@Serializable
data class PlayersStatisticsResponse(
    val get: String,
    val parameters: Map<String, String>,
    val errors: List<String>,
    val results: Int,
    val response: List<PlayerStatistics>,
) {
    @Serializable
    data class PlayerStatistics(
        val player: Player,
        val team: Team,
        val game: Game,
        val points: Int,
        val pos: String,
        val min: String,
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
        val comment: String? = null,
    )

    @Serializable
    data class Player(val id: Int, val firstname: String, val lastname: String)

    @Serializable
    data class Game(val id: Int)
}
