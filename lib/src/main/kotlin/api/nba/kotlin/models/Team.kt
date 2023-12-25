package api.nba.kotlin.models

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val id: Int,
    val name: String,
    val nickname: String,
    val code: String,
    val logo: String,
)
