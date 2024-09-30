package api.nfl.kotlin

import ApiClient
import HostsEnum
import io.ktor.client.engine.HttpClientEngine

/**
 * Represents a client for accessing the NBA API to retrieve NBA-related data.
 *
 * @property host The host of the API.
 * @property key The API key for authentication.
 * @property httpClientEngine The engine for HTTP client. If null, CIO engine is used as default.
 */
@Suppress("unused")
class ApiNflClient(
    host: HostsEnum,
    key: String,
    private val httpsClientEngine: HttpClientEngine? = null,
) : ApiClient(host, HostKeysEnum.NFL, key, httpsClientEngine)
