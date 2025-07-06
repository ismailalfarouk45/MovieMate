package com.ismail.core.domain.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.ismail.core.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

typealias Rid = R.id
typealias Rdrawable = R.drawable
const val TIME_ZONE_ID_JAKARTA = "Asia/Jakarta"
const val SERVER_DATE_FORMAT = "yyyy-MM-dd"
const val UI_DATE_FORMAT = "dd MMMM yyyy"

fun View.showSnackBar(text: String) =
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT)
        .show()

fun View.show(visible: Boolean) {
    this.isVisible = visible
}

fun String.commonImageUrl() = "https://image.tmdb.org/t/p/w500$this"

fun String.commonYoutubeUrl() = "https://www.youtube.com/embed/$this"

@SuppressLint("ObsoleteSdkInt")
fun Activity.transparentStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        val w: Window = this.window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}

fun String?.toDateFormat(
    oldFormat: String = SERVER_DATE_FORMAT,
    newFormat: String = UI_DATE_FORMAT,
    isLocale: Boolean = false,
): String {
    if (this.isNullOrEmpty()) return ""
    val calendar = Calendar.getInstance()
    try {
        val dateTimeMillis =
            SimpleDateFormat(oldFormat, isLocaleDate(isLocale)).parse(this)?.time ?: 0L
        calendar.timeInMillis = dateTimeMillis
        calendar.timeZone = TimeZone.getTimeZone(TIME_ZONE_ID_JAKARTA)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return SimpleDateFormat(newFormat, isLocaleDate(isLocale)).format(calendar.time)
}

fun isLocaleDate(
    isLocale: Boolean,
): Locale {
    return if (isLocale) Locale("id", "ID")
    else Locale.ENGLISH
}