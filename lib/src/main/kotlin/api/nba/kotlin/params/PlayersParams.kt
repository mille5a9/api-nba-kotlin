package api.nba.kotlin.params

class PlayersParams(
    private val id: Int? = null,
    private val name: String? = null,
    private val team: Int? = null,
    private val season: Int? = null,
    private val country: String? = null,
    private val search: String? = null,
) : EndpointParams {
    override fun getParams(): Map<String, String?> = mapOf(
        "id" to id?.toString(),
        "name" to name,
        "team" to team?.toString(),
        "season" to season?.toString(),
        "country" to country,
        "search" to search,
    )
}
