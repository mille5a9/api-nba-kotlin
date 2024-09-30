import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.serialization.gson.gson

open class ApiClient(
    protected val host: String,
    protected val key: String,
    httpClientEngine: HttpClientEngine? = null,
) {
    companion object {
        const val TOKEN_HEADER_KEY = "x-rapidapi-key"
    }

    protected val httpClient: HttpClient = HttpClient(httpClientEngine ?: CIO.create()) {
        install(ContentNegotiation) { gson { setLenient() } }
        install(HttpCache)
    }

    // Gets the Response from the API, where the 'response' property contains a list of T
    protected suspend inline fun <reified T : Any> get(
        endpoint: IEndpointEnum,
        params: IParameters? = null,
    ) = httpClient
        .get {
            url(host + endpoint)
            params?.getParams()?.forEach { (k, v) -> parameter(k, v) }
            header(TOKEN_HEADER_KEY, key)
        }.body<EndpointResponse<T>>()
}
