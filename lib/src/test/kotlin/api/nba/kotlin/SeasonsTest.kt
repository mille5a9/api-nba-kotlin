package api.nba.kotlin

import api.nba.kotlin.responses.EndpointResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SeasonsTest {

    private lateinit var apiNbaClient: ApiNbaClient

    private val expected: EndpointResponse<Int> = EndpointResponse(
        "seasons/",
        emptyMap(),
        emptyList(),
        9,
        listOf(2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023)
    )

    @Test
    fun seasonsReturns200(): Unit = runBlocking {
        apiNbaClient = ApiNbaClient(
            "host",
            "undefined",
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel("""
                        {"get":"seasons/","parameters":[],"errors":[],"results":9,"response":[2015,2016,2017,2018,2019,2020,2021,2022,2023]}
                    """.trimIndent()),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        )
        val response = apiNbaClient.getSeasons()
        assertEquals(response, expected)
    }
}