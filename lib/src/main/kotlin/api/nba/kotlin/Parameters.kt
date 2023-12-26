package api.nba.kotlin

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
    fun getParams(): Map<String, String?> = mapOf(
        "id" to id?.toString(),
        "game" to game?.toString(),
        "season" to season?.toString(),
        "team" to team?.toString(),
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
