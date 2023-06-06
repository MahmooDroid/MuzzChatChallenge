package com.example.muzzchatchallenge.presentation

import java.text.SimpleDateFormat
import java.util.Date

fun Long.convert(): String {
    val date = Date(this)
    val format = SimpleDateFormat("EEEE hh:mm")
    return format.format(date).toString()
}