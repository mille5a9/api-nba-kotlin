package api.nba.kotlin.models

import kotlinx.serialization.Serializable

@Serializable
data class Requests(
    val current: Int,
    val limit_day: Int,
)
