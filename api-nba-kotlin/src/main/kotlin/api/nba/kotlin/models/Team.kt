package api.nba.kotlin.models

/**
 * Represents an NBA team.
 *
 * @property id The ID of the team.
 * @property name The name of the team.
 * @property nickname The nickname of the team.
 * @property code The code of the team.
 * @property logo The URL of the team's logo.
 */
data class Team(
    val id: Int,
    val name: String,
    val nickname: String,
    val code: String,
    val logo: String,
)
