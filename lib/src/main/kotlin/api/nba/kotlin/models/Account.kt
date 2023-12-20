package api.nba.kotlin.models

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val firstname: String,
    val lastname: String,
    val email: String,
)
