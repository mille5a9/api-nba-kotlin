package api.nba.kotlin.enums

enum class HostEnum {
    RAPID_API,
    API_SPORTS;

    override fun toString(): String = when (this) {
        RAPID_API -> "https://api-nba-v1.p.rapidapi.com/"
        API_SPORTS -> "https://v2.nba.api-sports.io/"
    }
}