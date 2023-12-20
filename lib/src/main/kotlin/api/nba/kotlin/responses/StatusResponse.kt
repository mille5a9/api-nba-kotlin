package api.nba.kotlin.responses

import api.nba.kotlin.models.Account
import api.nba.kotlin.models.Requests
import api.nba.kotlin.models.Subscription
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
}
