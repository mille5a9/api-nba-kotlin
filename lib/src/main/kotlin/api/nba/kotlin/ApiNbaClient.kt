package api.nba.kotlin

import api.nba.kotlin.responses.StatusResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
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

    private suspend inline fun <reified T> get(endpoint: String) =
        httpClient.get {
            url("$host$endpoint")
            header("x-rapidapi-key", key)
        }.body<T>()

    suspend fun accountStatus() = get<StatusResponse>("/status")
}