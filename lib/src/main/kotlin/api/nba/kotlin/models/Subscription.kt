package api.nba.kotlin.models

import kotlinx.serialization.Serializable

@Serializable
data class Subscription(
    val plan: String,
    val end: String,
    val active: Boolean,
)
