package dev.vaibhav.newsapp.utils.serialize

import dev.vaibhav.newsapp.data.models.remote.SourceDto
import dev.vaibhav.newsapp.domain.models.Source
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

actual class SourceSerializer {

    actual fun serialize(source: SourceDto) = Json.encodeToString(source)

    actual fun deSerialize(source: String) = Json.decodeFromString<Source>(source)
}