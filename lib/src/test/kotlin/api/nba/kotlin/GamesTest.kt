package api.nba.kotlin

import api.nba.kotlin.models.Parameters
import api.nba.kotlin.models.Team
import api.nba.kotlin.responses.EndpointResponse
import api.nba.kotlin.responses.GamesResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GamesTest {

    private lateinit var apiNbaClient: ApiNbaClient

    private val expected: EndpointResponse<GamesResponse> = EndpointResponse(
        "games/",
        mapOf("id" to "10404"),
        emptyList(),
        1,
        listOf(
            GamesResponse(
                10404,
                "standard",
                2021,
                GamesResponse.Date(
                    "2022-02-12T00:00:00.000Z",
                    "2022-02-12T02:27:00.000Z",
                    "2:17"
                ),
                2,
                GamesResponse.Status(
                    null,
                    false,
                    3,
                    "Finished"
                ),
                GamesResponse.Periods(
                    4,
                    4,
                    false
                ),
                GamesResponse.Arena(
                    "Gainbridge Fieldhouse",
                    "Indianapolis",
                    "IN",
                    "USA"
                ),
                GamesResponse.Teams(
                    Team(
                        7,
                        "Cleveland Cavaliers",
                        "Cavaliers",
                        "CLE",
                        "https://upload.wikimedia.org/wikipedia/fr/thumb/0/06/Cavs_de_Cleveland_logo_2017.png/150px-Cavs_de_Cleveland_logo_2017.png"
                    ),
                    Team(
                        15,
                        "Indiana Pacers",
                        "Pacers",
                        "IND",
                        "https://upload.wikimedia.org/wikipedia/fr/thumb/c/cf/Pacers_de_l%27Indiana_logo.svg/1180px-Pacers_de_l%27Indiana_logo.svg.png"
                    )
                ),
                GamesResponse.Scores(
                    GamesResponse.Score(
                        35,
                        21,
                        GamesResponse.Series(
                            3,
                            0
                        ),
                        listOf(
                            "28",
                            "35",
                            "25",
                            "32"
                        ),
                        120
                    ),
                    GamesResponse.Score(
                        19,
                        38,
                        GamesResponse.Series(
                            0,
                            3
                        ),
                        listOf(
                            "47",
                            "27",
                            "22",
                            "17"
                        ),
                        113
                    )
                ),
                listOf(
                    "Ben Taylor",
                    "Dedric Taylor",
                    "Suyash Mehta"
                ),
                2,
                7,
                null
            )
        )
    )

    @Test
    fun gamesReturns200(): Unit = runBlocking {
        apiNbaClient = ApiNbaClient(
            "host",
            "undefined",
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel("""
{
    "get": "games/",
    "parameters": {
        "id": "10404"
    },
    "errors": [],
    "results": 1,
    "response": [
        {
            "id": 10404,
            "league": "standard",
            "season": 2021,
            "date": {
                "start": "2022-02-12T00:00:00.000Z",
                "end": "2022-02-12T02:27:00.000Z",
                "duration": "2:17"
            },
            "stage": 2,
            "status": {
                "clock": null,
                "halftime": false,
                "short": 3,
                "long": "Finished"
            },
            "periods": {
                "current": 4,
                "total": 4,
                "endOfPeriod": false
            },
            "arena": {
                "name": "Gainbridge Fieldhouse",
                "city": "Indianapolis",
                "state": "IN",
                "country": "USA"
            },
            "teams": {
                "visitors": {
                    "id": 7,
                    "name": "Cleveland Cavaliers",
                    "nickname": "Cavaliers",
                    "code": "CLE",
                    "logo": "https://upload.wikimedia.org/wikipedia/fr/thumb/0/06/Cavs_de_Cleveland_logo_2017.png/150px-Cavs_de_Cleveland_logo_2017.png"
                },
                "home": {
                    "id": 15,
                    "name": "Indiana Pacers",
                    "nickname": "Pacers",
                    "code": "IND",
                    "logo": "https://upload.wikimedia.org/wikipedia/fr/thumb/c/cf/Pacers_de_l%27Indiana_logo.svg/1180px-Pacers_de_l%27Indiana_logo.svg.png"
                }
            },
            "scores": {
                "visitors": {
                    "win": 35,
                    "loss": 21,
                    "series": {
                        "win": 3,
                        "loss": 0
                    },
                    "linescore": [
                        "28",
                        "35",
                        "25",
                        "32"
                    ],
                    "points": 120
                },
                "home": {
                    "win": 19,
                    "loss": 38,
                    "series": {
                        "win": 0,
                        "loss": 3
                    },
                    "linescore": [
                        "47",
                        "27",
                        "22",
                        "17"
                    ],
                    "points": 113
                }
            },
            "officials": [
                "Ben Taylor",
                "Dedric Taylor",
                "Suyash Mehta"
            ],
            "timesTied": 2,
            "leadChanges": 7,
            "nugget": null
        }
    ]
}
                    """.trimIndent()),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        )
        val response = apiNbaClient.getGames(Parameters(id = 10404))
        assertEquals(response, expected)
    }
}