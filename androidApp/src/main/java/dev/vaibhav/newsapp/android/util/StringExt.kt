package dev.vaibhav.newsapp.android.util

import android.text.Html
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.encodeUrl(): String = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())

fun String.fromHtml(): String = Html.fromHtml(this).toString()