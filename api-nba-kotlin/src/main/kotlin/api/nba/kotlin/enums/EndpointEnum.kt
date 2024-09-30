package api.nba.kotlin.enums

import IEndpointEnum

/**
 * Represents an enumeration of endpoints for an NBA API.
 *
 * @property STATUS Represents the endpoint for retrieving account status.
 * @property LEAGUES Represents the endpoint for retrieving leagues.
 * @property SEASONS Represents the endpoint for retrieving seasons.
 * @property TEAMS Represents the endpoint for retrieving teams.
 * @property PLAYERS Represents the endpoint for retrieving players.
 * @property GAMES Represents the endpoint for retrieving games.
 * @property STANDINGS Represents the endpoint for retrieving standings.
 * @property GAME_STATISTICS Represents the endpoint for retrieving game statistics.
 * @property TEAM_STATISTICS Represents the endpoint for retrieving team statistics.
 * @property PLAYER_STATISTICS Represents the endpoint for retrieving player statistics.
 */
enum class EndpointEnum : IEndpointEnum {
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

    /**
     * Returns the string representation of the object.
     *
     * @return the string representation of the object
     */
    override fun toString(): String =
        when (this) {
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
