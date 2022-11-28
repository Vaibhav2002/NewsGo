package dev.vaibhav.newsapp.domain

sealed class Topic(val topic: String) {
    object Headlines:Topic("Headlines")
    object Sports : Topic("sports")
    object Politics : Topic("politics")
    object Technology : Topic("technology")
    object Entertainment : Topic("entertainment")
}