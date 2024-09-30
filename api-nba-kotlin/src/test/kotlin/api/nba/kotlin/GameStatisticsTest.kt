package api.nba.kotlin

import api.nba.kotlin.models.Team
import EndpointResponse
import api.nba.kotlin.responses.GamesStatisticsResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameStatisticsTest {
    private lateinit var apiNbaClient: ApiNbaClient

    private val expected: EndpointResponse<GamesStatisticsResponse> = EndpointResponse(
        "games/statistics",
        mapOf("id" to "10403"),
        emptyList(),
        2,
        listOf(
            GamesStatisticsResponse(
                Team(
                    5,
                    "Charlotte Hornets",
                    "Hornets",
                    "CHA",
                    "https://upload.wikimedia.org/wikipedia/fr/thumb/f/f3/Hornets_de_Charlotte_logo.svg/1200px-Hornets_de_Charlotte_logo.svg.png",
                ),
                listOf(
                    GamesStatisticsResponse.GameStatistics(
                        15,
                        70,
                        28,
                        18,
                        24,
                        12,
                        141,
                        54,
                        97,
                        "55.7",
                        15,
                        23,
                        "65.2",
                        18,
                        42,
                        "42.9",
                        15,
                        36,
                        51,
                        36,
                        22,
                        13,
                        18,
                        2,
                        "22",
                        "240:00",
                    ),
                ),
            ),
            GamesStatisticsResponse(
                Team(
                    10,
                    "Detroit Pistons",
                    "Pistons",
                    "DET",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Logo_of_the_Detroit_Pistons.png/300px-Logo_of_the_Detroit_Pistons.png",
                ),
                listOf(
                    GamesStatisticsResponse.GameStatistics(
                        8,
                        52,
                        2,
                        13,
                        24,
                        12,
                        119,
                        48,
                        102,
                        "47.1",
                        11,
                        18,
                        "61.1",
                        12,
                        35,
                        "34.3",
                        16,
                        29,
                        45,
                        32,
                        20,
                        10,
                        18,
                        6,
                        "-22",
                        "240:00",
                    ),
                ),
            ),
        ),
    )

    @Test
    fun gamesStatisticsReturns200(): Unit = runBlocking {
        apiNbaClient = ApiNbaClient(
            HostsEnum.API_SPORTS,
            "undefined",
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel(
                        """
{
    "get": "games/statistics",
    "parameters": {
        "id": "10403"
    },
    "errors": [],
    "results": 2,
    "response": [
        {
            "team": {
                "id": 5,
                "name": "Charlotte Hornets",
                "nickname": "Hornets",
                "code": "CHA",
                "logo": "https://upload.wikimedia.org/wikipedia/fr/thumb/f/f3/Hornets_de_Charlotte_logo.svg/1200px-Hornets_de_Charlotte_logo.svg.png"
            },
            "statistics": [
                {
                    "fastBreakPoints": 15,
                    "pointsInPaint": 70,
                    "biggestLead": 28,
                    "secondChancePoints": 18,
                    "pointsOffTurnovers": 24,
                    "longestRun": 12,
                    "points": 141,
                    "fgm": 54,
                    "fga": 97,
                    "fgp": "55.7",
                    "ftm": 15,
                    "fta": 23,
                    "ftp": "65.2",
                    "tpm": 18,
                    "tpa": 42,
                    "tpp": "42.9",
                    "offReb": 15,
                    "defReb": 36,
                    "totReb": 51,
                    "assists": 36,
                    "pFouls": 22,
                    "steals": 13,
                    "turnovers": 18,
                    "blocks": 2,
                    "plusMinus": "22",
                    "min": "240:00"
                }
            ]
        },
        {
            "team": {
                "id": 10,
                "name": "Detroit Pistons",
                "nickname": "Pistons",
                "code": "DET",
                "logo": "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Logo_of_the_Detroit_Pistons.png/300px-Logo_of_the_Detroit_Pistons.png"
            },
            "statistics": [
                {
                    "fastBreakPoints": 8,
                    "pointsInPaint": 52,
                    "biggestLead": 2,
                    "secondChancePoints": 13,
                    "pointsOffTurnovers": 24,
                    "longestRun": 12,
                    "points": 119,
                    "fgm": 48,
                    "fga": 102,
                    "fgp": "47.1",
                    "ftm": 11,
                    "fta": 18,
                    "ftp": "61.1",
                    "tpm": 12,
                    "tpa": 35,
                    "tpp": "34.3",
                    "offReb": 16,
                    "defReb": 29,
                    "totReb": 45,
                    "assists": 32,
                    "pFouls": 20,
                    "steals": 10,
                    "turnovers": 18,
                    "blocks": 6,
                    "plusMinus": "-22",
                    "min": "240:00"
                }
            ]
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
        val response = apiNbaClient.getGameStatistics(10403)
        assertEquals(response, expected)
    }
}
