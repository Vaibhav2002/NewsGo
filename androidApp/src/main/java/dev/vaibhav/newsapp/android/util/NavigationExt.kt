package dev.vaibhav.newsapp.android.util

import android.util.Log
import androidx.navigation.NavController

fun NavController.navigateSafe(route: String) {
    runCatching { navigate(route) }
        .onFailure {
            Log.d("Nav Error ", it.stackTraceToString())
        }
}