package dev.vaibhav.newsapp.utils.serialize

import dev.vaibhav.newsapp.domain.models.Source

expect class SourceSerializer() : Serializer<Source> {
    override fun serialize(data: Source): String
    override fun deSerialize(data: String): Source
}