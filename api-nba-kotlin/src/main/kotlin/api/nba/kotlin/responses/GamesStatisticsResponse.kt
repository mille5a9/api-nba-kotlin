package api.nba.kotlin.responses

import api.nba.kotlin.models.Team
import kotlinx.serialization.Serializable

@Serializable
data class GamesStatisticsResponse(
    val team: Team,
    val statistics: List<GameStatistics>,
) {
    /**
     * Represents the statistics of a game.
     *
     * @property fastBreakPoints The number of fast break points.
     * @property pointsInPaint The number of points in the paint.
     * @property biggestLead The biggest lead in the game.
     * @property secondChancePoints The number of second chance points.
     * @property pointsOffTurnovers The number of points off turnovers.
     * @property longestRun The longest run in the game.
     * @property points The total number of points.
     * @property fgm The number of field goals made.
     * @property fga The number of field goals attempted.
     * @property fgp The field goal percentage as a string.
     * @property ftm The number of free throws made.
     * @property fta The number of free throws attempted.
     * @property ftp The free throw percentage as a string.
     * @property tpm The number of three-pointers made.
     * @property tpa The number of three-pointers attempted.
     * @property tpp The three-point percentage as a string.
     * @property offReb The number of offensive rebounds.
     * @property defReb The number of defensive rebounds.
     * @property totReb The total number of rebounds.
     * @property assists The number of assists.
     * @property pFouls The number of personal fouls.
     * @property steals The number of steals.
     * @property turnovers The number of turnovers.
     * @property blocks The number of blocks.
     * @property plusMinus The plus/minus of the team.
    */
    @Serializable
    data class GameStatistics(
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
