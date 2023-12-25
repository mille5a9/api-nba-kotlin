package api.nba.kotlin

import api.nba.kotlin.params.PlayersParams
import api.nba.kotlin.responses.PlayersResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlayersTest {

    private lateinit var apiNbaClient: ApiNbaClient

    private val expected: PlayersResponse = PlayersResponse(
        "players/",
        mapOf("id" to "553"),
        emptyList(),
        1,
        listOf(
            PlayersResponse.Player(
                553,
                "Lou",
                "Williams",
                PlayersResponse.Birth("1986-10-27", "USA"),
                PlayersResponse.Nba(2005, 16),
                PlayersResponse.Height("6", "2", "1.88"),
                PlayersResponse.Weight("175", "79.4"),
                "South Gwinnett HS (GA)",
                "South Gwinnett HS (GA)/USA",
                PlayersResponse.Leagues(
                    standard = PlayersResponse.League(6, true, "G"),
                    africa = null,
                    sacramento = null,
                    vegas = null,
                    utah = null,
                    orlando = null,
                )
            )
        )
    )

    @Test
    fun playersReturns200(): Unit = runBlocking {
        apiNbaClient = ApiNbaClient(
            "host",
            "undefined",
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel("""
{
    "get": "players/",
    "parameters": {
        "id": "553"
    },
    "errors": [],
    "results": 1,
    "response": [
        {
            "id": 553,
            "firstname": "Lou",
            "lastname": "Williams",
            "birth": {
                "date": "1986-10-27",
                "country": "USA"
            },
            "nba": {
                "start": 2005,
                "pro": 16
            },
            "height": {
                "feets": "6",
                "inches": "2",
                "meters": "1.88"
            },
            "weight": {
                "pounds": "175",
                "kilograms": "79.4"
            },
            "college": "South Gwinnett HS (GA)",
            "affiliation": "South Gwinnett HS (GA)/USA",
            "leagues": {
                "standard": {
                    "jersey": 6,
                    "active": true,
                    "pos": "G"
                }
            }
        }
    ]
}
                    """.trimIndent()),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        )
        val response = apiNbaClient.getPlayers(PlayersParams(id = 553))
        assertEquals(response, expected)
    }
}