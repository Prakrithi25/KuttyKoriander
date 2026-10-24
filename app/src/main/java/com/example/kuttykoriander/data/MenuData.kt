package com.example.kuttykoriander.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class MenuItem(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("price") val price: String,
    @SerialName("image") val image: String,
    @SerialName("category") val category: String
)

@Serializable
data class MenuNetwork(
    @SerialName("menu") val menu: List<MenuItem>
)

suspend fun fetchMenu(): List<MenuItem> {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    try {
        val response: MenuNetwork = client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json").body()
        client.close()
        return response.menu
    } catch (e: Exception) {
        client.close()
        return emptyList()
    }
}
