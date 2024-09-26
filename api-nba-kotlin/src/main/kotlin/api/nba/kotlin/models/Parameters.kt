package api.nba.kotlin.models

import com.faendir.kotlin.autodsl.AutoDsl

/**
 * Represents the parameters for making API requests.
 *
 * @property id The ID parameter.
 * @property game The game parameter.
 * @property season The season parameter.
 * @property team The team parameter.
 * @property league The league parameter.
 * @property conference The conference parameter.
 * @property division The division parameter.
 * @property code The code parameter.
 * @property h2h The h2h parameter.
 * @property date The date parameter.
 * @property live The live parameter.
 * @property country The country parameter.
 * @property search The search parameter.
 */
@AutoDsl
data class Parameters(
    private val id: Int? = null,
    private val game: Int? = null,
    private val season: Int? = null,
    private val team: Int? = null,
    private val league: String? = null,
    private val conference: String? = null,
    private val division: String? = null,
    private val code: String? = null,
    private val h2h: String? = null,
    private val date: String? = null,
    private val live: String? = null,
    private val country: String? = null,
    private val search: String? = null,
) {
    /**
     * Returns the parameters as a map.
     *
     * @return the parameters map.
     */
    fun getParams(): Map<String, Any?> =
        mapOf(
            "id" to id,
            "game" to game,
            "season" to season,
            "team" to team,
            "league" to league,
            "conference" to conference,
            "division" to division,
            "h2h" to h2h,
            "date" to date,
            "live" to live,
            "country" to country,
            "search" to search,
        )
}
