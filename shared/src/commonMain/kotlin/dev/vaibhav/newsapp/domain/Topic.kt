package dev.vaibhav.newsapp.domain

import kotlinx.serialization.Serializable

@Serializable
sealed class Topic(val topic: String) {
    @Serializable
    object Headlines : Topic("Headlines")

    @Serializable
    object Sports : Topic("Sports")

    @Serializable
    object Politics : Topic("Politics")

    @Serializable
    object Technology : Topic("Technology")

    @Serializable
    object Entertainment : Topic("Entertainment")


    companion object {
        fun fromTopic(topic: String): Topic = when (topic) {
            Headlines.topic -> Headlines
            Sports.topic -> Sports
            Politics.topic -> Politics
            Technology.topic -> Technology
            else -> Entertainment
        }
    }
}
