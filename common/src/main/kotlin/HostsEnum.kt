enum class HostKeysEnum(
    val rapidApi: String,
    val apiSports: String,
) {
    NBA(
        "https://api-nba-v1.p.rapidapi.com/",
        "https://v2.nba.api-sports.io/",
    ),
    NFL(
        "https://api-football-v1.p.rapidapi.com/v3/",
        "https://v3.football.api-sports.io/",
    ),
}

enum class HostsEnum {
    RAPID_API,
    API_SPORTS,
    ;

    fun url(hostKeysEnum: HostKeysEnum): String =
        when (this) {
            RAPID_API -> hostKeysEnum.rapidApi
            API_SPORTS -> hostKeysEnum.apiSports
        }
}
