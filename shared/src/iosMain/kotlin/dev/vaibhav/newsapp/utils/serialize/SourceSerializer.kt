package dev.vaibhav.newsapp.utils.serialize

import dev.vaibhav.newsapp.domain.models.Source
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

actual class SourceSerializer:Serializer<Source> {

    actual override fun serialize(data: Source) = json.encodeToString(data)

    actual override fun deSerialize(data: String) = json.decodeFromString<Source>(data)
}