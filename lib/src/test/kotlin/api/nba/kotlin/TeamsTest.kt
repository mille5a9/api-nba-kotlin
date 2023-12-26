package api.nba.kotlin

import api.nba.kotlin.models.Parameters
import api.nba.kotlin.responses.EndpointResponse
import api.nba.kotlin.responses.TeamsResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TeamsTest {

    private lateinit var apiNbaClient: ApiNbaClient

    private val expected: EndpointResponse<TeamsResponse> = EndpointResponse(
        "teams/",
        mapOf("id" to "1"),
        emptyList(),
        1,
        listOf(
            TeamsResponse(
                1,
                "Atlanta Hawks",
                "Hawks",
                "ATL",
                "Atlanta",
                "https://upload.wikimedia.org/wikipedia/fr/e/ee/Hawks_2016.png",
                false,
                true,
                TeamsResponse.Leagues(
                    standard = TeamsResponse.League("East", "Southeast"),
                    africa = null,
                    sacramento = TeamsResponse.League("East", "Southeast"),
                    vegas = TeamsResponse.League("summer", null),
                    utah = TeamsResponse.League("East", "Southeast"),
                    orlando = null,
                )
            )
        )
    )

    @Test
    fun teamsReturns200(): Unit = runBlocking {
        apiNbaClient = ApiNbaClient(
            "host",
            "undefined",
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel("""
{"get":"teams/","parameters":{"id":"1"},"errors":[],"results":1,"response":[{"id":1,"name":"Atlanta Hawks","nickname":"Hawks","code":"ATL","city":"Atlanta","logo":"https://upload.wikimedia.org/wikipedia/fr/e/ee/Hawks_2016.png","allStar":false,"nbaFranchise":true,"leagues":{"standard":{"conference":"East","division":"Southeast"},"vegas":{"conference":"summer","division":null},"utah":{"conference":"East","division":"Southeast"},"sacramento":{"conference":"East","division":"Southeast"}}}]}
                    """.trimIndent()),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        )
        val response = apiNbaClient.getTeams(Parameters(id = 1))
        assertEquals(response, expected)
    }
}