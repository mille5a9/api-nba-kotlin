package api.nba.kotlin

import api.nba.kotlin.models.Parameters
import api.nba.kotlin.models.Team
import api.nba.kotlin.responses.EndpointResponse
import api.nba.kotlin.responses.StandingsResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StandingsTest {

    private lateinit var apiNbaClient: ApiNbaClient

    private val expected: EndpointResponse<StandingsResponse> = EndpointResponse(
        "standings/",
        mapOf("league" to "standard", "season" to "2021", "team" to "1"),
        emptyList(),
        1,
        listOf(
            StandingsResponse(
                "standard",
                2021,
                Team(
                    1,
                    "Atlanta Hawks",
                    "Hawks",
                    "ATL",
                    "https://upload.wikimedia.org/wikipedia/fr/e/ee/Hawks_2016.png"
                ),
                StandingsResponse.Conference(
                    "east",
                    8,
                    26,
                    26
                ),
                StandingsResponse.Division(
                    "southeast",
                    2,
                    9,
                    7,
                    "10.0"
                ),
                StandingsResponse.Record(
                    27,
                    16,
                    43,
                    ".524",
                    7
                ),
                StandingsResponse.Record(
                    14,
                    25,
                    39,
                    ".476",
                    3
                ),
                "10.0",
                1,
                true,
                null
            )
        )
    )

    @Test
    fun standingsReturns200(): Unit = runBlocking {
        apiNbaClient = ApiNbaClient(
            "host",
            "undefined",
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel("""
{
    "get": "standings/",
    "parameters": {
        "league": "standard",
        "season": "2021",
        "team": "1"
    },
    "errors": [],
    "results": 1,
    "response": [
        {
            "league": "standard",
            "season": 2021,
            "team": {
                "id": 1,
                "name": "Atlanta Hawks",
                "nickname": "Hawks",
                "code": "ATL",
                "logo": "https://upload.wikimedia.org/wikipedia/fr/e/ee/Hawks_2016.png"
            },
            "conference": {
                "name": "east",
                "rank": 8,
                "win": 26,
                "loss": 26
            },
            "division": {
                "name": "southeast",
                "rank": 2,
                "win": 9,
                "loss": 7,
                "gamesBehind": "10.0"
            },
            "win": {
                "home": 27,
                "away": 16,
                "total": 43,
                "percentage": ".524",
                "lastTen": 7
            },
            "loss": {
                "home": 14,
                "away": 25,
                "total": 39,
                "percentage": ".476",
                "lastTen": 3
            },
            "gamesBehind": "10.0",
            "streak": 1,
            "winStreak": true,
            "tieBreakerPoints": null
        }
    ]
}
                    """.trimIndent()),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        )
        val response = apiNbaClient.getStandings(Parameters(league = "standard", season = 2021, team = 1))
        assertEquals(response, expected)
    }
}