package api.nba.kotlin.models

import kotlinx.serialization.Serializable

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
