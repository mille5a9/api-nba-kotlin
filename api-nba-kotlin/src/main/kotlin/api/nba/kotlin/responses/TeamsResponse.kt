package api.nba.kotlin.responses

/**
 * Represents a response object containing information about NBA teams.
 *
 * @param id The ID of the team.
 * @param name The name of the team.
 * @param nickname The nickname of the team.
 * @param code The code of the team.
 * @param city The city of the team.
 * @param logo The logo of the team.
 * @param allStar Indicates whether the team is an All-Star team.
 * @param nbaFranchise Indicates whether the team is an NBA franchise.
 * @param leagues The leagues associated with the team.
 */
data class TeamsResponse(
    val id: Int,
    val name: String,
    val nickname: String,
    val code: String,
    val city: String?,
    val logo: String?,
    val allStar: Boolean,
    val nbaFranchise: Boolean,
    val leagues: Leagues,
) {
    /**
     * Represents a collection of different leagues associated with NBA teams.
     *
     * @property standard The standard league associated with the NBA teams.
     * @property africa The African league associated with the NBA teams. Can be null.
     * @property sacramento The Sacramento league associated with the NBA teams. Can be null.
     * @property vegas The Las Vegas league associated with the NBA teams. Can be null.
     * @property utah The Utah league associated with the NBA teams. Can be null.
     * @property orlando The Orlando league associated with the NBA teams. Can be null.
     */
    data class Leagues(
        val standard: League? = null,
        val africa: League? = null,
        val sacramento: League? = null,
        val vegas: League? = null,
        val utah: League? = null,
        val orlando: League? = null,
    )

    /**
     * Represents a league associated with NBA teams.
     *
     * @property conference The conference associated with the NBA teams. Can be null.
     * @property division The division associated with the NBA teams. Can be null.
     */
    data class League(
        val conference: String?,
        val division: String?,
    )
}
