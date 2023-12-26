package api.nba.kotlin.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusResponse(
    val get: String,
    val parameters: List<String>,
    val errors: List<String>,
    val results: Int,
    val paging: Map<String, Int>,
    val response: Response,
) {

    @Serializable
    data class Response(
        val account: Account,
        val subscription: Subscription,
        val requests: Requests,
    )

    @Serializable
    data class Account(
        val firstname: String,
        val lastname: String,
        val email: String,
    )

    @Serializable
    data class Subscription(
        val plan: String,
        val end: String,
        val active: Boolean,
    )

    @Serializable
    data class Requests(
        val current: Int,
        @SerialName("limit_day") val limitDay: Int,
    )
}
