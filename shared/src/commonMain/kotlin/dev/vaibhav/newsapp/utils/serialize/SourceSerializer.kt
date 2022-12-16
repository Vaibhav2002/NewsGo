package dev.vaibhav.newsapp.utils.serialize

import dev.vaibhav.newsapp.data.models.remote.SourceDto
import dev.vaibhav.newsapp.domain.models.Source

expect class SourceSerializer() {

    fun serialize(source: SourceDto):String
    fun deSerialize(source:String): Source
}