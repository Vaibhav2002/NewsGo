package dev.vaibhav.newsapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
sealed class Topic(val topic: String) {
    @Serializable
    object Headlines : Topic("Headlines")

    @Serializable
    object Sports : Topic("Sports")

    @Serializable
    object Business : Topic("Business")

    @Serializable
    object Technology : Topic("Technology")

    @Serializable
    object Entertainment : Topic("Entertainment")

    @Serializable
    object Health : Topic("Health")

    @Serializable
    object Science : Topic("Science")


    companion object {
        fun fromTopic(topic: String): Topic = when (topic) {
            Headlines.topic -> Headlines
            Sports.topic -> Sports
            Business.topic -> Business
            Technology.topic -> Technology
            Health.topic -> Health
            Science.topic -> Science
            else -> Entertainment
        }
    }
}

val allTopics = listOf(
    Topic.Headlines,
    Topic.Sports,
    Topic.Business,
    Topic.Technology,
    Topic.Health,
    Topic.Science,
    Topic.Entertainment
)