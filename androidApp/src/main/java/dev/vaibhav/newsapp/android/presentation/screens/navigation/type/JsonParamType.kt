package dev.vaibhav.newsapp.android.presentation.screens.navigation.type

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson

class JsonParamType<T : Parcelable>(private val type: Class<T>) :
    NavType<T>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T = Gson().fromJson(value, type)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }
}