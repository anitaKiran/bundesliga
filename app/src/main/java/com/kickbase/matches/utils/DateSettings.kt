package com.kickbase.matches.utils

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Anita Kiran on 8/29/2022.
 */
object DateSettings {

    fun getDateAndMonth(dateString: String) : String{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = formatter.parse(dateString)
        val output = DateTimeFormatter.ofPattern("dd.MMM", Locale.GERMAN).format(date)
        return output
    }

    fun getTimeOfMatch(dateString: String) : String{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = formatter.parse(dateString)
        val timeOfMatch = DateTimeFormatter.ofPattern("HH:mm", Locale.GERMAN).format(date)
        return timeOfMatch
    }
}