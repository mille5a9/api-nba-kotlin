package api.nba.kotlin.responses

import api.nba.kotlin.models.Team
import kotlinx.serialization.Serializable

/**
 * Represents the response data for retrieving games from the NBA API.
 *
 * @param id The ID of the game.
 * @param league The league of the game.
 * @param season The season of the game.
 * @param date The date of the game.
 * @param stage The stage of the game.
 * @param status The status of the game.
 * @param periods The periods of the game.
 * @param arena The arena of the game.
 * @param teams The teams playing in the game.
 * @param scores The scores of the game.
 * @param officials The officials of the game.
 * @param timesTied The number of times the game was tied.
 * @param leadChanges The number of lead changes in the game.
 * @param nugget Additional information about the game.
 */
@Serializable
data class GamesResponse(
    val id: Int,
    val league: String,
    val season: Int,
    val date: Date,
    val stage: Int,
    val status: Status,
    val periods: Periods,
    val arena: Arena,
    val teams: Teams,
    val scores: Scores,
    val officials: List<String>,
    val timesTied: Int?,
    val leadChanges: Int?,
    val nugget: String?
) {

    /**
     * Represents a date with start and end timestamps and duration.
     *
     * @property start The start timestamp of the date.
     * @property end The end timestamp of the date.
     * @property duration The duration of the date.
     */
    @Serializable
    data class Date(
        val start: String,
        val end: String?,
        val duration: String?,
    )

    /**
     * Represents the status of a game.
     *
     * @property clock The current game clock time.
     * @property halftime Indicates if the game is currently at halftime.
     * @property short The short representation of the game status.
     * @property long The long representation of the game status.
     */
    @Serializable
    data class Status(
        val clock: String?,
        val halftime: Boolean?,
        val short: Int,
        val long: String,
    )

    /**
     * Represents the period information of a game.
     *
     * @property current The current period number.
     * @property total The total number of periods in the game.
     * @property endOfPeriod Indicates if the current period has ended.
     */
    @Serializable
    data class Periods(
        val current: Int,
        val total: Int,
        val endOfPeriod: Boolean?,
    )

    /**
     * Represents an arena where a game is played.
     *
     * @property name The name of the arena.
     * @property city The city where the arena is located.
     * @property state The state where the arena is located.
     * @property country The country where the arena is located.
     */
    @Serializable
    data class Arena(
        val name: String?,
        val city: String?,
        val state: String?,
        val country: String?,
    )

    /**
     * Represents the teams playing in a game.
     *
     * @property visitors The visiting team.
     * @property home The home team.
     */
    @Serializable
    data class Teams(
        val visitors: Team,
        val home: Team,
    )

    /**
     * Represents the scores of a game.
     *
     * @property visitors The visiting team's score.
     * @property home The home team's score.
     */
    @Serializable
    data class Scores(
        val visitors: Score,
        val home: Score,
    )

    /**
     * Represents the score of a team.
     *
     * @property win The number of wins.
     * @property loss The number of losses.
     * @property series The series score.
     * @property linescore The linescore of the game.
     * @property points The total number of points.
     */
    @Serializable
    data class Score(
        val win: Int?,
        val loss: Int?,
        val series: Series,
        val linescore: List<String>,
        val points: Int?,
    )

    /**
     * Represents the series score of a team.
     *
     * @property win The number of wins.
     * @property loss The number of losses.
     */
    @Serializable
    data class Series(
        val win: Int?,
        val loss: Int?,
    )
}
