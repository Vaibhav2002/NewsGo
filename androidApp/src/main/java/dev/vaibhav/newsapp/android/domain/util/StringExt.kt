package dev.vaibhav.newsapp.android.domain.util

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.encodeUrl(): String = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())