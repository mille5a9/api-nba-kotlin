package api.nba.kotlin.params

class GamesParams(
    private val id: Int? = null,
    private val date: String? = null,
    private val live: String? = null,
    private val league: String? = null,
    private val season: Int? = null,
    private val team: Int? = null,
    private val h2h: String? = null,
) : EndpointParams {
    override fun getParams(): Map<String, String?> = mapOf(
        "id" to id.toString(),
        "date" to date,
        "live" to live,
        "league" to league,
        "season" to season.toString(),
        "team" to team.toString(),
        "h2h" to h2h,
    )
}
