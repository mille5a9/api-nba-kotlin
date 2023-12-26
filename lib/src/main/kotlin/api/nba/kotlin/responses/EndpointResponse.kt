package api.nba.kotlin.responses

import api.nba.kotlin.serializers.MapSerializer
import kotlinx.serialization.Serializable

@Serializable
data class EndpointResponse<T : Any>(
    val get: String,
    @Serializable(with = MapSerializer::class) val parameters: Map<String, String>,
    val errors: List<String>,
    val results: Int,
    val response: List<T>,
)
