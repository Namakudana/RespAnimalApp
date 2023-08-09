package com.mardana.respanimalapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun generateRandomId(): String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    return (1..12)
        .map { charset.random() }
        .joinToString("")
}

object Pattern {
    const val datePattern = "EEEE, d MMMM yyyy"
    const val timePattern = "HH:mm"
    const val minutePattern = "mm:ss"
    const val dayPattern = "EEEE"
    const val dateTimePattern = "HH:mm - EEEE, d MMMM yyyy"
}

fun Long.timestampToDate(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(Date(this))
}

fun calculateScore(jawabanBenar: Int, totalSoal: Int): String {
    return when {
        ((jawabanBenar >= 0 && totalSoal > 0)) -> {
            val persentase = (jawabanBenar.toDouble() / totalSoal.toDouble()) * 10
            "%.1f".format(persentase)
        }
        else -> "0"
    }
}