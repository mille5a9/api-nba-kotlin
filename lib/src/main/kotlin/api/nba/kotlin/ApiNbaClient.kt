package api.nba.kotlin

import api.nba.kotlin.enums.EndpointEnum
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
}