package api.nba.kotlin.responses

import api.nba.kotlin.models.Team
import kotlinx.serialization.Serializable

@Serializable
data class StandingsResponse(
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
) {
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
