package api.nba.kotlin

import api.nba.kotlin.params.EndpointParams
import api.nba.kotlin.params.GamesParams
import api.nba.kotlin.params.TeamsParams
import api.nba.kotlin.responses.GamesResponse
import api.nba.kotlin.responses.LeaguesResponse
import api.nba.kotlin.responses.SeasonsResponse
import api.nba.kotlin.responses.StatusResponse
import api.nba.kotlin.responses.TeamsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
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
    private val httpClient: HttpClient = HttpClient(httpClientEngine ?: CIO.create()) {
        install(ContentNegotiation) { json() }
    }

    private suspend inline fun <reified T> get(
        endpoint: String,
        params: EndpointParams? = null,
    ) = httpClient.get {
        url("$host$endpoint")
        params?.getParams()?.forEach { (k, v) -> parameter(k, v) }
        header("x-rapidapi-key", key)
    }.body<T>()

    suspend fun getAccountStatus() = get<StatusResponse>("/status")

    suspend fun getSeasons() = get<SeasonsResponse>("/seasons")

    suspend fun getLeagues() = get<LeaguesResponse>("/leagues")

    suspend fun getGames(params: GamesParams) = get<GamesResponse>("/games", params)

    suspend fun getTeams(params: TeamsParams) = get<TeamsResponse>("/teams", params)
}