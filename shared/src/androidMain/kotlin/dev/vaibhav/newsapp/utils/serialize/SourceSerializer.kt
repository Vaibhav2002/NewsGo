package dev.vaibhav.newsapp.utils.serialize

import dev.vaibhav.newsapp.domain.models.Source

actual class SourceSerializer:Serializer<Source>{

    actual override fun serialize(data: Source) = JsonSerializer.serialize(data)

    actual override fun deSerialize(data: String) = JsonSerializer.deserialize<Source>(data)
}