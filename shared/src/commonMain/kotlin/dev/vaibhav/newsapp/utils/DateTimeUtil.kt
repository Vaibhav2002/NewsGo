package dev.vaibhav.newsapp.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtil {

    fun toEpochMillis(instant:String):Long{
        return instant.toInstant().toEpochMilliseconds()
    }

    fun toInstant(localDateTime: LocalDateTime):String{
        return localDateTime.toInstant(TimeZone.currentSystemDefault()).toString()
    }

    fun toLocalDateTime(instant:String):LocalDateTime{
        return instant.toInstant().toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
    }

    fun formatDateTime(dateTime:LocalDateTime):String{
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = if(dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
        val year = dateTime.year
        val hour = if(dateTime.hour < 10) "0${dateTime.hour}" else dateTime.hour
        val minute = if(dateTime.minute < 10) "0${dateTime.minute}" else dateTime.minute

        return buildString {
            append(month)
            append(" ")
            append(day)
            append(" ")
            append(year)
            append(", ")
            append(hour)
            append(":")
            append(minute)
        }
    }
}