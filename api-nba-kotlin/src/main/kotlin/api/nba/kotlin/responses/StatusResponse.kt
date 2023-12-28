package api.nba.kotlin.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the status response received from the API.
 *
 * @property get The status of the response.
 * @property parameters The list of parameters.
 * @property errors The list of errors.
 * @property results The number of results.
 * @property paging The map containing the paging information.
 * @property response The response object.
 */
@Serializable
data class StatusResponse(
    val get: String,
    val parameters: List<String>,
    val errors: List<String>,
    val results: Int,
    val paging: Map<String, Int>,
    val response: Response,
) {

    /**
     * Represents the response received from the API, containing information about the account, subscription, and requests.
     *
     * @property account The account information.
     * @property subscription The subscription information.
     * @property requests The*/
    @Serializable
    data class Response(
        val account: Account,
        val subscription: Subscription,
        val requests: Requests,
    )

    /**
     * Represents an account.
     *
     * @property firstname The first name of the account owner.
     * @property lastname The last name of the account owner.
     * @property email The email address of the account owner.
     */
    @Serializable
    data class Account(
        val firstname: String,
        val lastname: String,
        val email: String,
    )

    /**
     * Represents a subscription object.
     *
     * @property plan The plan of the subscription.
     * @property end The end date of the subscription.
     * @property active Indicates whether the subscription is active or not.
     */
    @Serializable
    data class Subscription(
        val plan: String,
        val end: String,
        val active: Boolean,
    )

    /**
     * Represents a set of requests made by an account.
     *
     * @property current The current number of requests made.
     * @property limitDay The daily limit for requests.
     */
    @Serializable
    data class Requests(
        val current: Int,
        @SerialName("limit_day") val limitDay: Int,
    )
}
