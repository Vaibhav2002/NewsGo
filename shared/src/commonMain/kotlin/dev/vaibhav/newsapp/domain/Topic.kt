package dev.vaibhav.newsapp.domain

sealed class Topic(val topic: String) {
    object Headlines:Topic("Headlines")
    object Sports : Topic("Sports")
    object Politics : Topic("Politics")
    object Technology : Topic("Technology")
    object Entertainment : Topic("Entertainment")
}