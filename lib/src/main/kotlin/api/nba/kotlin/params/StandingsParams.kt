package api.nba.kotlin.params

class StandingsParams(
    private val league: String? = null,
    private val season: Int? = null,
    private val team: Int? = null,
    private val conference: String? = null,
    private val division: String? = null,
) : EndpointParams {
    override fun getParams(): Map<String, String?> = mapOf(
        "league" to league,
        "season" to season?.toString(),
        "team" to team?.toString(),
        "conference" to conference,
        "division" to division,
    )
}
