package dev.vaibhav.newsapp.data.remote.dataSource.location

import dev.vaibhav.newsapp.data.models.remote.LocationDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object LocationDataSource {

    private const val BASE_URL = "http://ip-api.com/json"

    private val client = HttpClient() {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) { json() }
    }

    suspend fun fetchLocation() = runCatching {
        client.get(BASE_URL).body<LocationDto>()
    }.getOrNull()
}