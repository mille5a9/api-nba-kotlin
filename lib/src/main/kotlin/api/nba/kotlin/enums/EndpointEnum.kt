package api.nba.kotlin.enums

enum class EndpointEnum {
    STATUS,
    LEAGUES,
    SEASONS,
    TEAMS,
    PLAYERS,
    GAMES,
    STANDINGS,
    GAME_STATISTICS,
    TEAM_STATISTICS,
    PLAYER_STATISTICS,
    ;

    override fun toString(): String {
        return when (this) {
            STATUS -> "status/"
            LEAGUES -> "leagues/"
            SEASONS -> "seasons/"
            TEAMS -> "teams/"
            PLAYERS -> "players/"
            GAMES -> "games/"
            STANDINGS -> "standings/"
            GAME_STATISTICS -> "games/statistics/"
            TEAM_STATISTICS -> "teams/statistics/"
            PLAYER_STATISTICS -> "players/statistics/"
        }
    }
}