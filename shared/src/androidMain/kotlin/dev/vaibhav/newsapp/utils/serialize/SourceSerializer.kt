package dev.vaibhav.newsapp.utils.serialize

import dev.vaibhav.newsapp.data.models.remote.SourceDto
import dev.vaibhav.newsapp.domain.models.Source

actual class SourceSerializer{

    actual fun serialize(source: SourceDto) = JsonSerializer.serialize(source)

    actual fun deSerialize(source: String) = JsonSerializer.deserialize<Source>(source)
}