package api.nba.kotlin.responses

import api.nba.kotlin.models.Team

/**
 * Represents a response object for retrieving standings information.
 *
 * @param league The name of the league.
 * @param season The season.
 * @param team The team.
 * @param conference The conference.
 * @param division The division.
 * @param win The win record.
 * @param loss The loss record.
 * @param gamesBehind The games behind.
 * @param streak The streak.
 * @param winStreak The win streak status.
 * @param tieBreakerPoints The tie-breaker points (can be null).
 */
data class StandingsResponse(
    val league: String,
    val season: Int,
    val team: Team,
    val conference: Conference,
    val division: Division,
    val win: Record,
    val loss: Record,
    val gamesBehind: String?,
    val streak: Int?,
    val winStreak: Boolean,
    val tieBreakerPoints: Int?,
) {
    /**
     * Represents a conference in the NBA.
     *
     * @property name The name of the conference.
     * @property rank The rank of the conference.
     * @property win The number of wins.
     * @property loss The number of losses.
     */
    data class Conference(
        val name: String,
        val rank: Int,
        val win: Int,
        val loss: Int,
    )

    /**
     * Represents a division in the NBA.
     *
     * @property name The name of the division.
     * @property rank The rank of the division.
     * @property win The number of wins.
     * @property loss The number of losses.
     * @property gamesBehind The games behind as a string.
     */
    data class Division(
        val name: String,
        val rank: Int,
        val win: Int,
        val loss: Int,
        val gamesBehind: String?,
    )

    /**
     * Represents a record in the NBA standings.
     *
     * @property home The number of home wins.
     * @property away The number of away wins.
     * @property total The total number of wins.
     * @property percentage The win percentage as a string.
     * @property lastTen The number of wins in the last ten games.
     */
    data class Record(
        val home: Int,
        val away: Int,
        val total: Int,
        val percentage: String,
        val lastTen: Int,
    )
}
