package dev.vaibhav.newsapp.utils.serialize

import kotlinx.serialization.json.Json

interface Serializer<T> {

    val json: Json
        get() = Json { encodeDefaults = true }

    fun serialize(data: T): String
    fun deSerialize(data: String): T
}