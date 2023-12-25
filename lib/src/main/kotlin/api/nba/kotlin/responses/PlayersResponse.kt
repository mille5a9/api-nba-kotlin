package api.nba.kotlin.responses

import kotlinx.serialization.Serializable

@Serializable
data class PlayersResponse(
    val get: String,
    val parameters: Map<String, String>,
    val errors: List<String>,
    val results: Int,
    val response: List<Player>,
) {
    @Serializable
    data class Player(
        val id: Int,
        val firstname: String,
        val lastname: String,
        val birth: Birth,
        val nba: Nba,
        val height: Height,
        val weight: Weight,
        val college: String,
        val affiliation: String,
        val leagues: Leagues,
    )

    @Serializable
    data class Birth(
        val date: String,
        val country: String,
    )

    @Serializable
    data class Nba(
        val start: Int,
        val pro: Int,
    )

    @Serializable
    data class Height(
        val feets: String,
        val inches: String,
        val meters: String,
    )

    @Serializable
    data class Weight(
        val pounds: String,
        val kilograms: String,
    )

    @Serializable
    data class Leagues(
        val standard: League,
        val africa: League? = null,
        val sacramento: League? = null,
        val vegas: League? = null,
        val utah: League? = null,
        val orlando: League? = null,
    )

    @Serializable
    data class League(
        val jersey: Int,
        val active: Boolean,
        val pos: String,
    )
}
