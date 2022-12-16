package dev.vaibhav.newsapp.utils.serialize

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object JsonSerializer{

    inline fun <reified T> serialize(data:T) =  Json.encodeToString<T>(data)

    inline fun <reified T> deserialize(data:String) = Json.decodeFromString<T>(data)
}