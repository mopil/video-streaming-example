package com.example.streaming.util

import kotlin.math.round

fun Long.toKiloByte(): Double {
    return (round(this / 1024.0 * 100) / 100)
}

fun Long.toMegaByte(): Double {
    return (round(this / 1024.0 / 1024.0 * 100) / 100)
}

fun Long.toGigaByte(): Double {
    return (round(this / 1024.0 / 1024.0 / 1024.0 * 100) / 100)
}