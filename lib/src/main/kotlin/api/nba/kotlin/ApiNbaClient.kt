package api.nba.kotlin

import api.nba.kotlin.responses.StatusResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json


class ApiNbaClient(
    private val host: String = System.getenv("API_HOST"),
    private val key: String = System.getenv("API_KEY")
) {
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) { json() }
    }

    private suspend inline fun <reified T> get(endpoint: String) =
        httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = this@ApiNbaClient.host
                path(endpoint)
            }
            header("x-rapidapi-key", key)
        }.body<T>()

    suspend fun accountStatus() = get<StatusResponse>("/status")
}