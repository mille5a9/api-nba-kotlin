package api.nba.kotlin

import api.nba.kotlin.enums.EndpointEnum
import api.nba.kotlin.params.EndpointParams
import api.nba.kotlin.params.GamesParams
import api.nba.kotlin.params.GenericParams
import api.nba.kotlin.params.PlayersParams
import api.nba.kotlin.params.PlayersStatisticsParams
import api.nba.kotlin.params.StandingsParams
import api.nba.kotlin.params.TeamsParams
import api.nba.kotlin.responses.GamesResponse
import api.nba.kotlin.responses.LeaguesResponse
import api.nba.kotlin.responses.PlayersResponse
import api.nba.kotlin.responses.SeasonsResponse
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


class ApiNbaClient(
    private val host: String,
    private val key: String,
    httpClientEngine: HttpClientEngine? = null,
) {
    companion object {
        const val TOKEN_HEADER_KEY = "x-rapidapi-key"
    }

    private val httpClient: HttpClient = HttpClient(httpClientEngine ?: CIO.create()) {
        install(ContentNegotiation) { json() }
        install(HttpCache)
    }

    private suspend inline fun <reified T> get(
        endpoint: EndpointEnum,
        params: EndpointParams? = null,
    ) = httpClient.get {
        url(host + endpoint)
        params?.getParams()?.forEach { (k, v) -> parameter(k, v) }
        header(TOKEN_HEADER_KEY, key)
    }.body<T>()

    suspend fun getAccountStatus() =
        get<StatusResponse>(EndpointEnum.STATUS)

    suspend fun getSeasons() =
        get<SeasonsResponse>(EndpointEnum.SEASONS)

    suspend fun getLeagues() =
        get<LeaguesResponse>(EndpointEnum.LEAGUES)

    internal suspend fun getGames(params: GamesParams) =
        get<GamesResponse>(EndpointEnum.GAMES, params)

    internal suspend fun getTeams(params: TeamsParams) =
        get<TeamsResponse>(EndpointEnum.TEAMS, params)

    internal suspend fun getPlayers(params: PlayersParams) =
        get<PlayersResponse>(EndpointEnum.PLAYERS, params)

    internal suspend fun getStandings(params: StandingsParams) =
        get<StandingsResponse>(EndpointEnum.STANDINGS, params)

    internal suspend fun getPlayerStatistics(params: PlayersStatisticsParams) =
        get<PlayersStatisticsResponse>(EndpointEnum.PLAYER_STATISTICS, params)

    suspend fun getGameStatistics(id: Int) =
        get<GamesStatisticsResponse>(EndpointEnum.GAME_STATISTICS, GenericParams(id))

    suspend fun getTeamStatistics(id: Int, season: Int) =
        get<TeamsStatisticsResponse>(EndpointEnum.TEAM_STATISTICS, GenericParams(id, season))
}