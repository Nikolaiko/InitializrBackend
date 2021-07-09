package com.android.initializr.utils

import java.lang.StringBuilder
import java.util.*

fun generateTempFolderName(): String {
    val currentDate = Date()
    val calendar = GregorianCalendar()
    calendar.time = currentDate

    val builder = StringBuilder()
    builder.append("${calendar.get(Calendar.DAY_OF_MONTH)}-")
    builder.append("${calendar.get(Calendar.HOUR_OF_DAY)}-")
    builder.append("${calendar.get(Calendar.MINUTE)}-")
    builder.append("${calendar.get(Calendar.SECOND)}-")
    builder.append("${calendar.get(Calendar.MILLISECOND)}")

    return builder.toString()
}