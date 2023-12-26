package api.nba.kotlin.responses

import kotlinx.serialization.Serializable

@Serializable
data class TeamsResponse(
    val id: Int,
    val name: String,
    val nickname: String,
    val code: String,
    val city: String,
    val logo: String,
    val allStar: Boolean,
    val nbaFranchise: Boolean,
    val leagues: Leagues,
) {
    @Serializable
    data class Leagues(
        val standard: League,
        val africa: League? = null,
        val sacramento: League? = null,
        val vegas: League? = null,
        val utah: League? = null,
        val orlando: League? = null,
    )

    @Serializable
    data class League(
        val conference: String?,
        val division: String?,
    )
}
