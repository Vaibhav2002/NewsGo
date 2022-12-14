package dev.vaibhav.newsapp.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Serializer{

    inline fun <reified T> serialize(data:T) =  Json.encodeToString(data)

    inline fun <reified T> deserialize(data:String) = Json.decodeFromString<T>(data)
}