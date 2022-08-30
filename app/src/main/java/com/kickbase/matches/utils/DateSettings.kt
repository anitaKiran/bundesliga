package com.kickbase.matches.utils

import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Anita Kiran on 8/29/2022.
 */

// This class have methods for converting day, month, week day and time from the date provided
object DateSettings {

    //returns date and month
    fun getDateAndMonth(dateString: String) : String{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = formatter.parse(dateString)
        val output = DateTimeFormatter.ofPattern("dd.MMM", Locale.GERMAN).format(date)
        return output.dropLast(1) // remove full stop in the end of month
    }

    // return time of the match
    fun getTimeOfMatch(dateString: String) : String{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = formatter.parse(dateString)
        val timeOfMatch = DateTimeFormatter.ofPattern("HH:mm", Locale.GERMAN).format(date)
        return timeOfMatch
    }

    // returns day of the week
    fun getWeekDay(dateString: String) : String{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = formatter.parse(dateString)
        val day = DateTimeFormatter.ofPattern("EEEE", Locale.GERMAN).format(date)
        return day
    }
}