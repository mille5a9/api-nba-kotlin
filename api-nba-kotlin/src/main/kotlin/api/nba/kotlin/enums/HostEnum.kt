package api.nba.kotlin.enums

/**
 * Enumeration representing the available hosts for API endpoints.
 */
enum class HostEnum {
    RAPID_API,
    API_SPORTS,
    ;

    /**
     * Returns the endpoint represented by the HostEnum.
     *
     * @return the string representation of the host.
     */
    override fun toString(): String =
        when (this) {
            RAPID_API -> "https://api-nba-v1.p.rapidapi.com/"
            API_SPORTS -> "https://v2.nba.api-sports.io/"
        }
}
