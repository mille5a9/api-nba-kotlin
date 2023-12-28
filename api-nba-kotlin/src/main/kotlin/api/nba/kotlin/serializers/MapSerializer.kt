package api.nba.kotlin.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * This class is a Serializer for a Map of type "Map<String, String>".
 *
 * This serializer is used to serialize and deserialize a map with string keys and string values.
 * It implements the KSerializer interface for the specified map type, allowing it to be used with the kotlinx.serialization library.
 *
 * The MapSerializer class includes two private properties:
 *  - listSerializer: A ListSerializer of type String, used for deserializing the map if an exception occurs while deserializing with the mapSerializer.
 *  - mapSerializer: A MapSerializer of type (String, String), used for actual serialization and deserialization of the map.
 *
 * The class implements the KSerializer<Map<String, String>> interface, which requires the following:
 *  - descriptor: A property that represents the structure of the serialized class. In this case, it is the descriptor of mapSerializer.
 *  - deserialize(decoder): A function that deserializes the Map<String, String> from the decoder.
 *    If an exception occurs while deserializing the map using listSerializer, an empty map is returned.
 *    Otherwise, the map is deserialized using mapSerializer.
 *  - serialize(encoder, value): A function that serializes the Map<String, String> value using the mapSerializer.
 */
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