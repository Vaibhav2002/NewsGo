package dev.vaibhav.newsapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Saved(
    val saveId:Long,
    val isSaved:Boolean
)
