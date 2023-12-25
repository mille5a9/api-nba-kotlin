package api.nba.kotlin.params

data class GenericParams(
    private val id: Int,
    private val season: Int? = null,
) : EndpointParams {
    override fun getParams(): Map<String, String?> = mapOf(
        "id" to id.toString(),
        "season" to season.toString(),
    )
}
