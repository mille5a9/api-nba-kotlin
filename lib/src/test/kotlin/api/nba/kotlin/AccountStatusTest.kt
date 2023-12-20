package api.nba.kotlin

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountStatusTest {
    @Test fun accountStatusReturns200(): Unit = runBlocking {
        val apiNbaClient = ApiNbaClient()
        val response = apiNbaClient.accountStatus().also { println(it) }
        assertEquals(response.response.account.email, "atmiller192@gmail.com")
    }
}