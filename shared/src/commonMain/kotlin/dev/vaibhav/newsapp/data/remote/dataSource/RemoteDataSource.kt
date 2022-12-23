package dev.vaibhav.newsapp.data.remote.dataSource

import dev.vaibhav.newsapp.utils.Constants.BASE_URL
import dev.vaibhav.newsapp.utils.Secrets.API_KEY
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

abstract class RemoteDataSource {

    val baseUrl = BASE_URL
    val apiKey = API_KEY

    val client = HttpClient() {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json()
        }
    }

    suspend inline fun <reified T> get(
        url: String,
        crossinline builder: HttpRequestBuilder.() -> Unit
    ): T = api {
        client.get("$baseUrl/$url") {
            builder()
            url {
                parameters.append("apiKey", apiKey)
            }
        }.body()
    }


    suspend fun <T> api(call: suspend () -> T): T = try {
        call()
    } catch (e: Exception) {
        throw e
    }

}