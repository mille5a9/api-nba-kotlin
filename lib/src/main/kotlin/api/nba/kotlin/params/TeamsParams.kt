package api.nba.kotlin.params

class TeamsParams(
    private val id: Int? = null,
    private val name: String? = null,
    private val code: String? = null,
    private val league: String? = null,
    private val conference: Int? = null,
    private val division: Int? = null,
    private val search: String? = null,
) : EndpointParams {
    override fun getParams(): Map<String, String?> = mapOf(
        "id" to id?.toString(),
        "name" to name,
        "code" to code,
        "league" to league,
        "conference" to conference?.toString(),
        "division" to division?.toString(),
        "search" to search,
    )
}
