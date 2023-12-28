package api.nba.kotlin.responses

import kotlinx.serialization.Serializable

/**
 * Represents the response object for team statistics.
 *
 * @property games The number of games played.
 * @property fastBreakPoints The number of points scored in fast breaks.
 * @property pointsInPaint The number of points scored in the paint.
 * @property biggestLead The biggest lead in points.
 * @property secondChancePoints The number of points scored from second chances.
 * @property pointsOffTurnovers The number of points scored from turnovers.
 * @property longestRun The length of the longest run in points.
 * @property points The total number of points scored.
 * @property fgm The total number of field goals made.
 * @property fga The total number of field goals attempted.
 * @property fgp The field goal percentage as a string.
 * @property ftm The total number of free throws made.
 * @property fta The total number of free throws attempted.
 * @property ftp The free throw percentage as a string.
 * @property tpm The total number of three-pointers made.
 * @property tpa The total number of three-pointers attempted.
 * @property tpp The three-point percentage as a string.
 * @property offReb The total number of offensive rebounds.
 * @property defReb The total number of defensive rebounds.
 * @property totReb The total number of rebounds.
 * @property assists The total number of assists.
 * @property pFouls The total number of personal fouls.
 * @property steals The total number of steals.
 * @property turnovers The total number of turnovers.
 * @property blocks The total number of blocks.
 * @property plusMinus The plus/minus of the team.
 */
@Serializable
data class TeamsStatisticsResponse(
    val games: Int,
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
    val plusMinus: Int,
)
