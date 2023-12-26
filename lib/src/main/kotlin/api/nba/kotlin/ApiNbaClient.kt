package api.nba.kotlin

import api.nba.kotlin.enums.EndpointEnum
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


class ApiNbaClient(
    private val host: String,
    private val key: String,
    httpClientEngine: HttpClientEngine? = null,
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
        url(host + endpoint)
        params?.getParams()?.forEach { (k, v) -> parameter(k, v) }
        header(TOKEN_HEADER_KEY, key)
    }.body<EndpointResponse<T>>()

    suspend fun getAccountStatus() = httpClient.get {
        url(host + EndpointEnum.STATUS)
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

    suspend fun getSeasons() =
        get<Int>(EndpointEnum.SEASONS)

    suspend fun getLeagues() =
        get<String>(EndpointEnum.LEAGUES)

    suspend fun getGameStatistics(gameId: Int) =
        get<GamesStatisticsResponse>(EndpointEnum.GAME_STATISTICS, Parameters(id = gameId))

    suspend fun getTeamStatistics(teamId: Int, season: Int) =
        get<TeamsStatisticsResponse>(EndpointEnum.TEAM_STATISTICS, Parameters(id = teamId, season = season))

    suspend fun getGameById(gameId: Int) =
        get<GamesResponse>(EndpointEnum.GAMES, Parameters(id = gameId))

    suspend fun getGamesBetweenTwoTeams(teamId1: Int, teamId2: Int) =
        get<GamesResponse>(EndpointEnum.GAMES, Parameters(h2h = "$teamId1-$teamId2"))

    suspend fun getLiveGames() = get<GamesResponse>(EndpointEnum.GAMES, Parameters(live = "all"))

    suspend fun getGamesBySeason(season: Int) =
        get<GamesResponse>(EndpointEnum.GAMES, Parameters(season = season))

    // Date format "YYYY-mm-DD"
    suspend fun getGamesByDate(date: String) = get<GamesResponse>(EndpointEnum.GAMES, Parameters(date = date))

    suspend fun getGamesByTeamAndSeason(teamId: Int, season: Int) =
        get<GamesResponse>(EndpointEnum.GAMES, Parameters(team = teamId, season = season))

    suspend fun getAllTeams() = get<TeamsResponse>(EndpointEnum.TEAMS)

    suspend fun getTeamById(teamId: Int) = get<TeamsResponse>(EndpointEnum.TEAMS, Parameters(id = teamId))

    suspend fun getTeamsByConference(conference: String) =
        get<TeamsResponse>(EndpointEnum.TEAMS, Parameters(conference = conference))

    suspend fun getTeamsByDivision(division: String) =
        get<TeamsResponse>(EndpointEnum.TEAMS, Parameters(division = division))

    suspend fun getTeamByCode(code: String) =
        get<TeamsResponse>(EndpointEnum.TEAMS, Parameters(code = code))

    suspend fun getPlayersByTeamAndSeason(teamId: Int, season: Int) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, Parameters(team = teamId, season = season))

    suspend fun getPlayerById(playerId: Int) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, Parameters(id = playerId))

    suspend fun getPlayersByCountry(country: String) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, Parameters(country = country))

    suspend fun getStandingsByConferenceAndSeason(conference: String, season: Int) =
        get<StandingsResponse>(EndpointEnum.STANDINGS, Parameters(conference = conference, season = season))

    suspend fun getStandingsByDivisionAndSeason(division: String, season: Int) =
        get<StandingsResponse>(EndpointEnum.STANDINGS, Parameters(division = division, season = season))

    suspend fun getStandingsByTeamAndSeason(teamId: Int, season: Int) =
        get<StandingsResponse>(EndpointEnum.STANDINGS, Parameters(team = teamId, season = season))

    suspend fun getPlayersStatisticsByTeamAndSeason(teamId: Int, season: Int) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, Parameters(team = teamId, season = season))

    suspend fun getPlayerStatisticsByPlayerAndSeason(playerId: Int, season: Int) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, Parameters(id = playerId, season = season))

    suspend fun getPlayersStatisticsByGame(gameId: Int) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, Parameters(game = gameId))

    suspend fun searchTeams(query: String) =
        get<TeamsResponse>(EndpointEnum.TEAMS, Parameters(search = query))

    suspend fun searchPlayers(query: String) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, Parameters(search = query))
}