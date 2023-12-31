package api.nba.kotlin.responses

import kotlinx.serialization.Serializable

/**
 * Represents a response object for retrieving player information.
 *
 * @property id The unique identifier of the player.
 * @property firstname The first name of the player.
 * @property lastname The last name of the player.
 * @property birth The birth information of the player.
 * @property nba The NBA information of the player.
 * @property height The height information of the player.
 * @property weight The weight information of the player.
 * @property college The college attended by the player.
 * @property affiliation The affiliation of the player.
 * @property leagues The leagues in which the player has played.
 */
@Serializable
data class PlayersResponse(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val birth: Birth,
    val nba: Nba,
    val height: Height,
    val weight: Weight,
    val college: String?,
    val affiliation: String?,
    val leagues: Leagues,
) {
    /**
     * Represents the birth information of a player.
     *
     * @property date The birth date of the player in the format "YYYY-mm-DD".
     * @property country The country of birth of the player.
     */
    @Serializable
    data class Birth(
        val date: String?,
        val country: String?,
    )

    /**
     * Represents the NBA information of a player.
     *
     * @property start The starting season of the player in the NBA.
     * @property pro The number of seasons the player has played in the NBA.
     */
    @Serializable
    data class Nba(
        val start: Int,
        val pro: Int,
    )

    /**
     * Represents the height information of a player.
     *
     * @property feets The height of the player in feet.
     * @property inches The height of the player in inches.
     * @property meters The height of the player in meters.
     */
    @Serializable
    data class Height(
        val feets: String?,
        val inches: String?,
        val meters: String?,
    )

    /**
     * Represents the weight information of a player.
     *
     * @property pounds The weight of the player in pounds.
     * @property kilograms The weight of the player in kilograms.
     */
    @Serializable
    data class Weight(
        val pounds: String?,
        val kilograms: String?,
    )

    /**
     * Represents the leagues in which a player has played.
     *
     * @property standard The standard league in which the player has played.
     * @property africa The Africa league in which the player has played.
     * @property sacramento The Sacramento league in which the player has played.
     * @property vegas The Vegas league in which the player has played.
     * @property utah The Utah league in which the player has played.
     * @property orlando The Orlando league in which the player has played.
     */
    @Serializable
    data class Leagues(
        val standard: League? = null,
        val africa: League? = null,
        val sacramento: League? = null,
        val vegas: League? = null,
        val utah: League? = null,
        val orlando: League? = null,
    )

    /**
     * Represents a league in which a player has played.
     *
     * @property jersey The jersey number of the player in the league.
     * @property active Indicates if the player is currently active in the league.
     * @property pos The position of the player in the league.
     */
    @Serializable
    data class League(
        val jersey: Int?,
        val active: Boolean,
        val pos: String,
    )
}
