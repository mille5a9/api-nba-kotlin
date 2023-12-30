package api.nba.kotlin

import api.nba.kotlin.enums.EndpointEnum
import api.nba.kotlin.enums.HostEnum
import api.nba.kotlin.models.Parameters
import api.nba.kotlin.responses.EndpointResponse
import api.nba.kotlin.responses.GamesResponse
import api.nba.kotlin.responses.PlayersResponse
import api.nba.kotlin.responses.StandingsResponse
import api.nba.kotlin.responses.GamesStatisticsResponse
import api.nba.kotlin.responses.PlayersStatisticsResponse
import api.nba.kotlin.responses.StatusResponse
import api.nba.kotlin.responses.TeamsResponse
import api.nba.kotlin.responses.TeamsStatisticsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


/**
 * Represents a client for accessing the NBA API to retrieve NBA-related data.
 *
 * @property host The host of the API.
 * @property key The API key for authentication.
 * @property httpClientEngine The engine for HTTP client. If null, CIO engine is used as default.
 */
class ApiNbaClient(
    private val host: HostEnum,
    private val key: String,
    private val httpClientEngine: HttpClientEngine? = null,
) {
    companion object {
        const val TOKEN_HEADER_KEY = "x-rapidapi-key"
    }

    private val httpClient: HttpClient = HttpClient(httpClientEngine ?: CIO.create()) {
        install(ContentNegotiation) { json(Json { isLenient = true }) }
        install(HttpCache)
    }

    // Gets the Response from the API, where the 'response' property contains a list of T
    private suspend inline fun <reified T : Any> get(
        endpoint: EndpointEnum,
        params: Parameters? = null,
    ) = httpClient.get {
        url(host.toString() + endpoint)
        params?.getParams()?.forEach { (k, v) -> parameter(k, v) }
        header(TOKEN_HEADER_KEY, key)
    }.body<EndpointResponse<T>>()

    /**
     * Retrieves the account status by making a GET request to the API. Does not count against daily usage limits.
     *
     * @return the account status response.
     */
    suspend fun getAccountStatus() = httpClient.get {
        url(host.toString() + EndpointEnum.STATUS)
        header(TOKEN_HEADER_KEY, key)
    }.body<StatusResponse>()

    internal suspend fun getGames(params: Parameters) =
        get<GamesResponse>(EndpointEnum.GAMES, params)

    internal suspend fun getTeams(params: Parameters) =
        get<TeamsResponse>(EndpointEnum.TEAMS, params)

    internal suspend fun getPlayers(params: Parameters) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, params)

    internal suspend fun getStandings(params: Parameters) =
        get<StandingsResponse>(EndpointEnum.STANDINGS, params)

    internal suspend fun getPlayerStatistics(params: Parameters) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, params)

    /**
     * Retrieves the list of seasons from the NBA API.
     *
     * @return The list of seasons as an EndpointResponse<Int>.
     */
    suspend fun getSeasons() =
        get<Int>(EndpointEnum.SEASONS)

    /**
     * Retrieves the list of leagues from the NBA API.
     *
     * @return The list of leagues as an EndpointResponse<String>.
     */
    suspend fun getLeagues() =
        get<String>(EndpointEnum.LEAGUES)

    /**
     * Retrieves the game statistics for a specific game.
     *
     * @param gameId The ID of the game.
     * @return The game statistics as an EndpointResponse<GamesStatisticsResponse> object.
     */
    suspend fun getGameStatistics(gameId: Int) =
        get<GamesStatisticsResponse>(EndpointEnum.GAME_STATISTICS, Parameters(id = gameId))

    /**
     * Retrieves the team statistics for a specific team in a given season.
     *
     * @param teamId The ID of the team.
     * @param season The season.
     * @return The team statistics response as an EndpointResponse<TeamsStatisticsResponse> object.
     */
    suspend fun getTeamStatistics(teamId: Int, season: Int) =
        get<TeamsStatisticsResponse>(EndpointEnum.TEAM_STATISTICS, Parameters(id = teamId, season = season))

    /**
     * Retrieves the game information for a specific game by its ID.
     *
     * @param gameId The ID of the game.
     * @return The game information as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getGameById(gameId: Int) =
        get<GamesResponse>(EndpointEnum.GAMES, Parameters(id = gameId))

    /**
     * Retrieves the games between two teams from the NBA API.
     *
     * @param teamId1 The ID of the first team.
     * @param teamId2 The ID of the second team.
     * @return The games between the two teams as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getGamesBetweenTwoTeams(teamId1: Int, teamId2: Int) =
        get<GamesResponse>(EndpointEnum.GAMES, Parameters(h2h = "$teamId1-$teamId2"))

    /**
     * Retrieves the live games from the NBA API.
     *
     * This method sends a GET request to the specified endpoint with the "live" parameter set to "all".
     *
     * @return The live games as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getLiveGames() = get<GamesResponse>(EndpointEnum.GAMES, Parameters(live = "all"))

    /**
     * Retrieves the list of games for a specific season from the NBA API.
     *
     * @param season The season for which to retrieve the games.
     * @return The list of games as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getGamesBySeason(season: Int) =
        get<GamesResponse>(EndpointEnum.GAMES, Parameters(season = season))

    /**
     * Retrieves the list of games for a specific date from the NBA API.
     *
     * @param date The date for which to retrieve the games. Required format is "YYYY-mm-dd"
     * @return The list of games as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getGamesByDate(date: String) = get<GamesResponse>(EndpointEnum.GAMES, Parameters(date = date))

    /**
     * Retrieves the games for a specific team and season from the NBA API.
     *
     * @param teamId The ID of the team.
     * @param season The season.
     * @return The games for the specified team and season as an EndpointResponse<GamesResponse> object.
     */
    suspend fun getGamesByTeamAndSeason(teamId: Int, season: Int) =
        get<GamesResponse>(EndpointEnum.GAMES, Parameters(team = teamId, season = season))

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
    suspend fun getTeamById(teamId: Int) = get<TeamsResponse>(EndpointEnum.TEAMS, Parameters(id = teamId))

    /**
     * Retrieves the teams in a specific conference from the NBA API.
     *
     * @param conference The conference name.
     * @return The list of teams in the specified conference as an EndpointResponse<TeamsResponse> object.
     */
    suspend fun getTeamsByConference(conference: String) =
        get<TeamsResponse>(EndpointEnum.TEAMS, Parameters(conference = conference))

    /**
     * Retrieves the teams in a specific division from the NBA API.
     *
     * @param division The name of the division.
     * @return The list of teams in the specified division as an EndpointResponse<TeamsResponse> object.
     */
    suspend fun getTeamsByDivision(division: String) =
        get<TeamsResponse>(EndpointEnum.TEAMS, Parameters(division = division))

    /**
     * Retrieves the team information for a specific team by its code.
     *
     * @param code The code of the team.
     * @return The team information as an EndpointResponse<TeamsResponse> object.
     */
    suspend fun getTeamByCode(code: String) =
        get<TeamsResponse>(EndpointEnum.TEAMS, Parameters(code = code))

    /**
     * Retrieves the list of players for a specific team and season from the NBA API.
     *
     * @param teamId The ID of the team.
     * @param season The season.
     * @return The list of players for the specified team and season as an EndpointResponse<PlayersResponse> object.
     */
    suspend fun getPlayersByTeamAndSeason(teamId: Int, season: Int) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, Parameters(team = teamId, season = season))

    /**
     * Retrieves the player information for a specific player by their ID.
     *
     * @param playerId The ID of the player.
     * @return The player information as an EndpointResponse<PlayersResponse> object.
     */
    suspend fun getPlayerById(playerId: Int) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, Parameters(id = playerId))

    /**
     * Retrieves the list of players for a specific country from the NBA API.
     *
     * @param country The name of the country.
     * @return The list of players for the specified country as an EndpointResponse<PlayersResponse> object.
     */
    suspend fun getPlayersByCountry(country: String) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, Parameters(country = country))

    /**
     * Retrieves the standings by conference and season from the NBA API.
     *
     * @param conference The name of the conference.
     * @param season The season.
     * @return The standings response as an EndpointResponse<StandingsResponse> object.
     */
    suspend fun getStandingsByConferenceAndSeason(conference: String, season: Int) =
        get<StandingsResponse>(EndpointEnum.STANDINGS, Parameters(conference = conference, season = season, league = "standard"))

    /**
     * Retrieves the standings by division and season from the NBA API.
     *
     * @param division The name of the division.
     * @param season The season.
     * @return The standings response as an EndpointResponse<StandingsResponse> object.
     */
    suspend fun getStandingsByDivisionAndSeason(division: String, season: Int) =
        get<StandingsResponse>(EndpointEnum.STANDINGS, Parameters(division = division, season = season, league = "standard"))

    /**
     * Retrieves the standings for a specific team and season from the NBA API.
     *
     * @param teamId The ID of the team.
     * @param season The season.
     * @return The standings response as an EndpointResponse<StandingsResponse> object.
     */
    suspend fun getStandingsByTeamAndSeason(teamId: Int, season: Int) =
        get<StandingsResponse>(EndpointEnum.STANDINGS, Parameters(team = teamId, season = season, league = "standard"))

    /**
     * Retrieves the players statistics for a specific team and season from the NBA API.
     *
     * @param teamId The ID of the team.
     * @param season The season.
     * @return The players statistics response as an EndpointResponse<PlayersStatisticsResponse> object.
     */
    suspend fun getPlayersStatisticsByTeamAndSeason(teamId: Int, season: Int) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, Parameters(team = teamId, season = season))

    /**
     * Retrieves the player statistics for a specific player and season from the NBA API.
     *
     * @param playerId The ID of the player.
     * @param season The season.
     * @return The player statistics response as an EndpointResponse<PlayersStatisticsResponse> object.
     */
    suspend fun getPlayerStatisticsByPlayerAndSeason(playerId: Int, season: Int) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, Parameters(id = playerId, season = season))

    /**
     * Retrieves the players statistics for a specific game.
     *
     * @param gameId The ID of the game.
     * @return The players statistics response as an EndpointResponse<PlayersStatisticsResponse> object.
     */
    suspend fun getPlayersStatisticsByGame(gameId: Int) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, Parameters(game = gameId))

    /**
     * Suspended method that searches for teams based on the provided query.
     *
     * @param query The search query.
     * @return An instance of [EndpointResponse] containing a list of teams that match the search query.
     */
    suspend fun searchTeams(query: String) =
        get<TeamsResponse>(EndpointEnum.TEAMS, Parameters(search = query))

    /**
     * Suspended method that searches for players based on the provided query.
     *
     * @param query The search query.
     * @return An instance of [EndpointResponse] containing a list of players that match the search query.
     */
    suspend fun searchPlayers(query: String) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, Parameters(search = query))
}