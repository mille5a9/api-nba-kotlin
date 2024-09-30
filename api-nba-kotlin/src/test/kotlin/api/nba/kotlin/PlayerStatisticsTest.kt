package api.nba.kotlin

import api.nba.kotlin.enums.HostEnum
import api.nba.kotlin.models.NbaParameters
import api.nba.kotlin.models.Team
import EndpointResponse
import api.nba.kotlin.responses.PlayersStatisticsResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlayerStatisticsTest {
    private lateinit var apiNbaClient: ApiNbaClient

    private val expected: EndpointResponse<PlayersStatisticsResponse> = EndpointResponse(
        "players/statistics",
        mapOf("id" to "236", "game" to "8137", "season" to "2020"),
        emptyList(),
        1,
        listOf(
            PlayersStatisticsResponse(
                PlayersStatisticsResponse.Player(
                    236,
                    "Buddy",
                    "Hield",
                ),
                Team(
                    30,
                    "Sacramento Kings",
                    "Kings",
                    "SAC",
                    "https://upload.wikimedia.org/wikipedia/fr/thumb/9/95/Kings_de_Sacramento_logo.svg/1200px-Kings_de_Sacramento_logo.svg.png",
                ),
                PlayersStatisticsResponse.Game(
                    8137,
                ),
                23,
                "SG",
                "23:01",
                8,
                17,
                "47.1",
                2,
                2,
                "100",
                5,
                11,
                "45.5",
                0,
                0,
                0,
                1,
                1,
                1,
                0,
                0,
                "-14",
                null,
            ),
        ),
    )

    @Test
    fun gamesStatisticsReturns200(): Unit = runBlocking {
        apiNbaClient = ApiNbaClient(
            HostEnum.API_SPORTS,
            "undefined",
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel(
                        """
{
    "get": "players/statistics",
    "parameters": {
        "id": "236",
        "game": "8137",
        "season": "2020"
    },
    "errors": [],
    "results": 1,
    "response": [
        {
            "player": {
                "id": 236,
                "firstname": "Buddy",
                "lastname": "Hield"
            },
            "team": {
                "id": 30,
                "name": "Sacramento Kings",
                "nickname": "Kings",
                "code": "SAC",
                "logo": "https://upload.wikimedia.org/wikipedia/fr/thumb/9/95/Kings_de_Sacramento_logo.svg/1200px-Kings_de_Sacramento_logo.svg.png"
            },
            "game": {
                "id": 8137
            },
            "points": 23,
            "pos": "SG",
            "min": "23:01",
            "fgm": 8,
            "fga": 17,
            "fgp": "47.1",
            "ftm": 2,
            "fta": 2,
            "ftp": "100",
            "tpm": 5,
            "tpa": 11,
            "tpp": "45.5",
            "offReb": 0,
            "defReb": 0,
            "totReb": 0,
            "assists": 1,
            "pFouls": 1,
            "steals": 1,
            "turnovers": 0,
            "blocks": 0,
            "plusMinus": "-14",
            "comment": null
        }
    ]
}
                        """.trimIndent(),
                    ),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json"),
                )
            },
        )
        val response = apiNbaClient.getPlayerStatistics(
            NbaParameters(id = 236, game = 8137, season = 2020),
        )
        assertEquals(response, expected)
    }
}
