package dev.vaibhav.newsapp.domain.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Saved(
    val isSaved: Boolean,
    val timeStamp: LocalDateTime,
)
