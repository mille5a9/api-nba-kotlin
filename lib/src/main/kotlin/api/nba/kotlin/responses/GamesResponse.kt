package api.nba.kotlin.responses

import kotlinx.serialization.Serializable

@Serializable
data class GamesResponse(
    val get: String,
    val parameters: Map<String, String>,
    val errors: List<String>,
    val results: Int,
    val response: List<Game>,
) {
    @Serializable
    data class Game(
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
        val timesTied: Int,
        val leadChanges: Int,
        val nugget: String?
    )

    @Serializable
    data class Date(
        val start: String,
        val end: String,
        val duration: String,
    )

    @Serializable
    data class Status(
        val clock: String?,
        val halftime: Boolean,
        val short: Int,
        val long: String,
    )

    @Serializable
    data class Periods(
        val current: Int,
        val total: Int,
        val endOfPeriod: Boolean,
    )

    @Serializable
    data class Arena(
        val name: String,
        val city: String,
        val state: String,
        val country: String,
    )

    @Serializable
    data class Teams(
        val visitors: Team,
        val home: Team,
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
    data class Scores(
        val visitors: Score,
        val home: Score,
    )

    @Serializable
    data class Score(
        val win: Int,
        val loss: Int,
        val series: Series,
        val linescore: List<String>,
        val points: Int,
    )

    @Serializable
    data class Series(
        val win: Int,
        val loss: Int,
    )
}
