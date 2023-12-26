package api.nba.kotlin

import api.nba.kotlin.enums.HostEnum
import api.nba.kotlin.responses.EndpointResponse
import api.nba.kotlin.responses.TeamsStatisticsResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TeamStatisticsTest {

    private lateinit var apiNbaClient: ApiNbaClient

    private val expected: EndpointResponse<TeamsStatisticsResponse> = EndpointResponse(
        "teams/statistics",
        mapOf("id" to "1", "season" to "2021"),
        emptyList(),
        1,
        listOf(
            TeamsStatisticsResponse(
                94,
                894,
                4198,
                1242,
                1157,
                1494,
                1053,
                10567,
                3846,
                8246,
                "46.7",
                1675,
                2071,
                "80.7",
                1200,
                3235,
                "36.9",
                939,
                3176,
                4115,
                2280,
                1781,
                673,
                1101,
                394,
                94,
            )
        )
    )

    @Test
    fun gamesStatisticsReturns200(): Unit = runBlocking {
        apiNbaClient = ApiNbaClient(
            HostEnum.API_SPORTS,
            "undefined",
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel("""
{"get":"teams/statistics","parameters":{"id":"1","season":"2021"},"errors":[],"results":1,"response":[{"games":94,"fastBreakPoints":894,"pointsInPaint":4198,"biggestLead":1242,"secondChancePoints":1157,"pointsOffTurnovers":1494,"longestRun":1053,"points":10567,"fgm":3846,"fga":8246,"fgp":"46.7","ftm":1675,"fta":2071,"ftp":"80.7","tpm":1200,"tpa":3235,"tpp":"36.9","offReb":939,"defReb":3176,"totReb":4115,"assists":2280,"pFouls":1781,"steals":673,"turnovers":1101,"blocks":394,"plusMinus":94}]}
                    """.trimIndent()),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        )
        val response = apiNbaClient.getTeamStatistics(1, 2021)
        assertEquals(response, expected)
    }
}