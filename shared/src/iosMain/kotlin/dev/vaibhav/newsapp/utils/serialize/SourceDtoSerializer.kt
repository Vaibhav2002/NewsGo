package dev.vaibhav.newsapp.utils.serialize

import dev.vaibhav.newsapp.data.models.remote.SourceDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

actual class SourceDtoSerializer : Serializer<SourceDto> {
    actual override fun serialize(data: SourceDto) = json.encodeToString(data)

    actual override fun deSerialize(data: String) = json.decodeFromString<SourceDto>(data)

}