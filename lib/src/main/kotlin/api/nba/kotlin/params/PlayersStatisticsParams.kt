package api.nba.kotlin.params

class PlayersStatisticsParams(
    private val id: Int? = null,
    private val game: Int? = null,
    private val team: Int? = null,
    private val season: Int? = null,
) : EndpointParams {
    override fun getParams(): Map<String, String?> = mapOf(
        "id" to id?.toString(),
        "game" to game?.toString(),
        "team" to team?.toString(),
        "season" to season?.toString(),
    )
}
