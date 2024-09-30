package api.nba.kotlin

import ApiClient
import EndpointResponse
import HostsEnum
import api.nba.kotlin.enums.EndpointEnum
import api.nba.kotlin.models.NbaParameters
import api.nba.kotlin.responses.GamesResponse
import api.nba.kotlin.responses.GamesStatisticsResponse
import api.nba.kotlin.responses.PlayersResponse
import api.nba.kotlin.responses.PlayersStatisticsResponse
import api.nba.kotlin.responses.StandingsResponse
import api.nba.kotlin.responses.TeamsResponse
import api.nba.kotlin.responses.TeamsStatisticsResponse
import io.ktor.client.engine.HttpClientEngine

/**
 * Represents a client for accessing the NBA API to retrieve NBA-related data.
 *
 * @property host The host of the API.
 * @property key The API key for authentication.
 * @property httpClientEngine The engine for HTTP client. If null, CIO engine is used as default.
 */
@Suppress("unused")
class ApiNbaClient(
    host: HostsEnum,
    key: String,
    private val httpClientEngine: HttpClientEngine? = null,
) : ApiClient(host, HostKeysEnum.NBA, key, httpClientEngine) {
    internal suspend fun getGames(params: NbaParameters) =
        get<GamesResponse>(EndpointEnum.GAMES, params)

    internal suspend fun getTeams(params: NbaParameters) =
        get<TeamsResponse>(EndpointEnum.TEAMS, params)

    internal suspend fun getPlayers(params: NbaParameters) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, params)

    internal suspend fun getStandings(params: NbaParameters) =
        get<StandingsResponse>(EndpointEnum.STANDINGS, params)

    internal suspend fun getPlayerStatistics(params: NbaParameters) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, params)

    /**
     * Retrieves the list of seasons from the NBA API.
     *
     * @return The list of seasons as an EndpointResponse<Int>.
     */
    suspend fun getSeasons() = get<Int>(EndpointEnum.SEASONS)

    /**
     * Retrieves the list of leagues from the NBA API.
     *
     * @return The list of leagues as an EndpointResponse<String>.
     */
    suspend fun getLeagues() = get<String>(EndpointEnum.LEAGUES)

    /**
     * Retrieves the game statistics for a specific game.
     *
     * @param gameId The ID of the game.
     * @return The game statistics as an EndpointResponse<GamesStatisticsResponse> object.
     */
    suspend fun getGameStatistics(gameId: Int) =
        get<GamesStatisticsResponse>(EndpointEnum.GAME_STATISTICS, NbaParameters(id = gameId))

    /**
     * Retrieves the team statistics for a specific team in a given season.
     *
     * @param teamId The ID of the team.
     * @param season The season.
     * @return The team statistics response as an EndpointResponse<TeamsStatisticsResponse> object.
     */
    suspend fun getTeamStatistics(
        teamId: Int,
        season: Int,
    ) = get<TeamsStatisticsResponse>(EndpointEnum.TEAM_STATISTICS, NbaParameters(id = teamId, season = season))

    /**
     * Retrieves the game information for a specific game by its ID.
     *
     * @param gameId The ID of the game.
     * @return The game information as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getGameById(gameId: Int) = get<GamesResponse>(EndpointEnum.GAMES, NbaParameters(id = gameId))

    /**
     * Retrieves the games between two teams from the NBA API.
     *
     * @param teamId1 The ID of the first team.
     * @param teamId2 The ID of the second team.
     * @return The games between the two teams as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getGamesBetweenTwoTeams(
        teamId1: Int,
        teamId2: Int,
    ) = get<GamesResponse>(EndpointEnum.GAMES, NbaParameters(h2h = "$teamId1-$teamId2"))

    /**
     * Retrieves the live games from the NBA API.
     *
     * This method sends a GET request to the specified endpoint with the "live" parameter set to "all".
     *
     * @return The live games as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getLiveGames() = get<GamesResponse>(EndpointEnum.GAMES, NbaParameters(live = "all"))

    /**
     * Retrieves the list of games for a specific season from the NBA API.
     *
     * @param season The season for which to retrieve the games.
     * @return The list of games as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getGamesBySeason(season: Int) = get<GamesResponse>(EndpointEnum.GAMES, NbaParameters(season = season))

    /**
     * Retrieves the list of games for a specific date from the NBA API.
     *
     * @param date The date for which to retrieve the games. Required format is "YYYY-mm-dd"
     * @return The list of games as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getGamesByDate(date: String) = get<GamesResponse>(EndpointEnum.GAMES, NbaParameters(date = date))

    /**
     * Retrieves the games for a specific team and season from the NBA API.
     *
     * @param teamId The ID of the team.
     * @param season The season.
     * @return The games for the specified team and season as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getGamesByTeamAndSeason(
        teamId: Int,
        season: Int,
    ) = get<GamesResponse>(EndpointEnum.GAMES, NbaParameters(team = teamId, season = season))

    /**
     * Retrieves the list of all NBA teams.
     *
     * @return The list of teams as an EndpointResponse<TeamsResponse> object.
     */
    suspend fun getAllTeams() = get<TeamsResponse>(EndpointEnum.TEAMS)

    /**
     * Retrieves the team information for a specific team by its ID.
     *
     * @param teamId The ID of the team.
     * @return The team information as an EndpointResponse<TeamsResponse> object.
     */
    suspend fun getTeamById(teamId: Int) = get<TeamsResponse>(EndpointEnum.TEAMS, NbaParameters(id = teamId))

    /**
     * Retrieves the teams in a specific conference from the NBA API.
     *
     * @param conference The conference name.
     * @return The list of teams in the specified conference as an EndpointResponse<TeamsResponse> object.
     */
    suspend fun getTeamsByConference(conference: String) =
        get<TeamsResponse>(EndpointEnum.TEAMS, NbaParameters(conference = conference))

    /**
     * Retrieves the teams in a specific division from the NBA API.
     *
     * @param division The name of the division.
     * @return The list of teams in the specified division as an EndpointResponse<TeamsResponse> object.
     */
    suspend fun getTeamsByDivision(division: String) =
        get<TeamsResponse>(EndpointEnum.TEAMS, NbaParameters(division = division))

    /**
     * Retrieves the team information for a specific team by its code.
     *
     * @param code The code of the team.
     * @return The team information as an EndpointResponse<TeamsResponse> object.
     */
    suspend fun getTeamByCode(code: String) = get<TeamsResponse>(EndpointEnum.TEAMS, NbaParameters(code = code))

    /**
     * Retrieves the list of players for a specific team and season from the NBA API.
     *
     * @param teamId The ID of the team.
     * @param season The season.
     * @return The list of players for the specified team and season as an EndpointResponse<PlayersResponse> object.
     */
    suspend fun getPlayersByTeamAndSeason(teamId: Int, season: Int) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, NbaParameters(team = teamId, season = season))

    /**
     * Retrieves the player information for a specific player by their ID.
     *
     * @param playerId The ID of the player.
     * @return The player information as an EndpointResponse<PlayersResponse> object.
     */
    suspend fun getPlayerById(playerId: Int) = get<PlayersResponse>(EndpointEnum.PLAYERS, NbaParameters(id = playerId))

    /**
     * Retrieves the list of players for a specific country from the NBA API.
     *
     * @param country The name of the country.
     * @return The list of players for the specified country as an EndpointResponse<PlayersResponse> object.
     */
    suspend fun getPlayersByCountry(country: String) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, NbaParameters(country = country))

    /**
     * Retrieves the standings by conference and season from the NBA API.
     *
     * @param conference The name of the conference.
     * @param season The season.
     * @return The standings response as an EndpointResponse<StandingsResponse> object.
     */
    suspend fun getStandingsByConferenceAndSeason(conference: String, season: Int) =
        get<StandingsResponse>(
            EndpointEnum.STANDINGS,
            NbaParameters(conference = conference, season = season, league = "standard"),
        )

    /**
     * Retrieves the standings by division and season from the NBA API.
     *
     * @param division The name of the division.
     * @param season The season.
     * @return The standings response as an EndpointResponse<StandingsResponse> object.
     */
    suspend fun getStandingsByDivisionAndSeason(division: String, season: Int) =
        get<StandingsResponse>(
            EndpointEnum.STANDINGS,
            NbaParameters(division = division, season = season, league = "standard"),
        )

    /**
     * Retrieves the standings for a specific team and season from the NBA API.
     *
     * @param teamId The ID of the team.
     * @param season The season.
     * @return The standings response as an EndpointResponse<StandingsResponse> object.
     */
    suspend fun getStandingsByTeamAndSeason(teamId: Int, season: Int) =
        get<StandingsResponse>(
            EndpointEnum.STANDINGS,
            NbaParameters(team = teamId, season = season, league = "standard"),
        )

    /**
     * Retrieves the players statistics for a specific team and season from the NBA API.
     *
     * @param teamId The ID of the team.
     * @param season The season.
     * @return The players statistics response as an EndpointResponse<PlayersStatisticsResponse> object.
     */
    suspend fun getPlayersStatisticsByTeamAndSeason(teamId: Int, season: Int) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, NbaParameters(team = teamId, season = season))

    /**
     * Retrieves the player statistics for a specific player and season from the NBA API.
     *
     * @param playerId The ID of the player.
     * @param season The season.
     * @return The player statistics response as an EndpointResponse<PlayersStatisticsResponse> object.
     */
    suspend fun getPlayerStatisticsByPlayerAndSeason(playerId: Int, season: Int) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, NbaParameters(id = playerId, season = season))

    /**
     * Retrieves the players statistics for a specific game.
     *
     * @param gameId The ID of the game.
     * @return The players statistics response as an EndpointResponse<PlayersStatisticsResponse> object.
     */
    suspend fun getPlayersStatisticsByGame(gameId: Int) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, NbaParameters(game = gameId))

    /**
     * Suspended method that searches for teams based on the provided query.
     *
     * @param query The search query.
     * @return An instance of [EndpointResponse] containing a list of teams that match the search query.
     */
    suspend fun searchTeams(query: String) = get<TeamsResponse>(EndpointEnum.TEAMS, NbaParameters(search = query))

    /**
     * Suspended method that searches for players based on the provided query.
     *
     * @param query The search query.
     * @return An instance of [EndpointResponse] containing a list of players that match the search query.
     */
    suspend fun searchPlayers(query: String) = get<PlayersResponse>(EndpointEnum.PLAYERS, NbaParameters(search = query))
}
