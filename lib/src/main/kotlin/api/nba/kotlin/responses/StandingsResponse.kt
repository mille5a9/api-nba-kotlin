package api.nba.kotlin.responses

import kotlinx.serialization.Serializable

@Serializable
data class StandingsResponse(
    val get: String,
    val parameters: Map<String, String>,
    val errors: List<String>,
    val results: Int,
    val response: List<Standing>,
) {
    @Serializable
    data class Standing(
        val league: String,
        val season: Int,
        val team: Team,
        val conference: Conference,
        val division: Division,
        val win: Record,
        val loss: Record,
        val gamesBehind: String,
        val streak: Int,
        val winStreak: Boolean,
        val tieBreakerPoints: Int?,
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
    data class Conference(
        val name: String,
        val rank: Int,
        val win: Int,
        val loss: Int,
    )

    @Serializable
    data class Division(
        val name: String,
        val rank: Int,
        val win: Int,
        val loss: Int,
        val gamesBehind: String,
    )

    @Serializable
    data class Record(
        val home: Int,
        val away: Int,
        val total: Int,
        val percentage: String,
        val lastTen: Int,
    )
}
