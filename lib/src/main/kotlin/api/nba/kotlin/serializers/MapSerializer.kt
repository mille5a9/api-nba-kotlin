package api.nba.kotlin.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object MapSerializer: KSerializer<Map<String, String>> {
    private val listSerializer = ListSerializer(String.serializer())
    private val mapSerializer = MapSerializer(String.serializer(), String.serializer())

    override val descriptor: SerialDescriptor = mapSerializer.descriptor

    override fun deserialize(decoder: Decoder): Map<String, String> =
        try {
            listSerializer.deserialize(decoder).run { mapOf() }
        } catch (e: Exception) {
            mapSerializer.deserialize(decoder)
        }

    override fun serialize(encoder: Encoder, value: Map<String, String>) = mapSerializer.serialize(encoder, value)
}