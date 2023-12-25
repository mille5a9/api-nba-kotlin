package api.nba.kotlin

import api.nba.kotlin.responses.LeaguesResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LeaguesTest {

    private lateinit var apiNbaInternalClient: ApiNbaInternalClient

    private val expected: LeaguesResponse = LeaguesResponse(
        "leagues/",
        emptyList(),
        emptyList(),
        6,
        listOf("africa","orlando","sacramento","standard","utah","vegas")
    )

    @Test
    fun seasonsReturns200(): Unit = runBlocking {
        apiNbaInternalClient = ApiNbaInternalClient(
            "host",
            "undefined",
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel("""
                        {"get":"leagues/","parameters":[],"errors":[],"results":6,"response":["africa","orlando","sacramento","standard","utah","vegas"]}
                    """.trimIndent()),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        )
        val response = apiNbaInternalClient.getLeagues()
        assertEquals(response, expected)
    }
}