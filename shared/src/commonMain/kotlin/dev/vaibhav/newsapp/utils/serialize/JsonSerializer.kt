package dev.vaibhav.newsapp.utils.serialize

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object JsonSerializer {

    val json = Json { coerceInputValues = true }

    inline fun <reified T> serialize(data:T) =  json.encodeToString<T>(data)

    inline fun <reified T> deserialize(data:String) = json.decodeFromString<T>(data)
}