package dev.vaibhav.newsapp.utils.serialize

import dev.vaibhav.newsapp.data.models.remote.SourceDto

actual class SourceDtoSerializer : Serializer<SourceDto> {
    actual override fun serialize(data: SourceDto) = JsonSerializer.serialize(data)

    actual override fun deSerialize(data: String) = JsonSerializer.deserialize<SourceDto>(data)

}