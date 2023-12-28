package api.nba.kotlin

import api.nba.kotlin.enums.HostEnum
import api.nba.kotlin.responses.StatusResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AccountStatusTest {

    private lateinit var apiNbaClient: ApiNbaClient

    private val expected: StatusResponse = StatusResponse(
        "status",
        emptyList(),
        emptyList(),
        0,
        mapOf("current" to 1, "total" to 1),
        StatusResponse.Response(
            StatusResponse.Account(
                firstname = "John",
                lastname = "Smith",
                email = "test@gmail.com"
            ),
            StatusResponse.Subscription(
                plan = "Free",
                end = "2024-12-20T00:00:00+00:00",
                active = true
            ),
            StatusResponse.Requests(
                current = 0,
                limitDay = 100
            ),
        ),
    )

    @Test
    fun accountStatusReturns200(): Unit = runBlocking {
        apiNbaClient = ApiNbaClient(
            HostEnum.API_SPORTS,
            "undefined",
            MockEngine { _ ->
                respond(
                    content = ByteReadChannel("""
                        {"get":"status","parameters":[],"errors":[],"results":0,"paging":{"current":1,"total":1},"response":{"account":{"firstname":"John","lastname":"Smith","email":"test@gmail.com"},"subscription":{"plan":"Free","end":"2024-12-20T00:00:00+00:00","active":true},"requests":{"current":0,"limit_day":100}}}
                    """.trimIndent()),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        )
        val response = apiNbaClient.getAccountStatus()
        assertEquals(response, expected)
    }
}