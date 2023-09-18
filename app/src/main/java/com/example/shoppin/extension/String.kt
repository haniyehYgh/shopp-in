package com.example.shoppin.utils

import android.text.TextUtils
import com.example.shoppin.utils.date.DateUtils

import com.example.shoppin.utils.date.DateUtils.FARSI_LOCALE
import java.util.*


private val persianNumbers = arrayOf(
    "۰", "۱", "۲", "۳", "۴", "۵",
    "۶", "۷", "۸", "۹"
)



fun getJalaliFormattedDate(date: Long?, withTime: Boolean, embedLTR: Boolean): String? {
    return getJalaliFormattedDate(Date(date!!), withTime, embedLTR)
}

fun getJalaliFormattedDate(date: Date?, withTime: Boolean, embedLTR: Boolean): String? {
    val format = if (withTime) "yyyy/MM/dd - HH:mm" else "yyyy/MM/dd"
    return getJalaliFormattedDate(date, format, embedLTR)
}



fun getJalaliFormattedDate(date: Date?, format: String?, embedLTR: Boolean): String? {
    val cal = Calendar.getInstance()
    cal.time = date
    val jalaliDate: String = DateUtils.getLocaleDate(FARSI_LOCALE, date, format, true)
    return if (embedLTR) {
        embedLTR(jalaliDate)
    } else {
        jalaliDate
    }
}
fun embedLTR(text: String): String {
    return if (TextUtils.isEmpty(text)) {
        ""
    } else '\u202A'.toString() + text + '\u202A'
}


fun formatPanWithMask(pan: String?): String? {
    val separator = "   "
    return pan?.let { formatPanWithMask(it, separator) }
}

fun formatPanWithMask(pan: String, separator: String): String? {
    var pan = pan
    pan = embedLTR(pan.substring(0, 6) + "******" + pan.substring(12))
    return pan.substring(0, 5)+separator + pan.substring(5, 9) + separator + pan.substring(9, 13) + separator + pan.substring(13)
}

fun addThousandSeparator(s: String?): String? {
    var s = s ?: return ""
    if (s.contains(".")) {
        s = s.substring(0, s.indexOf("."))
    }
    val f = StringBuilder()
    val temp = s.replace("[^0-9۰-۹]+".toRegex(), "")
    for (i in 0 until temp.length) {
        f.append(temp[i])
        if ((temp.length - 1 - i) % 3 == 0 && temp.length - 1 - i != 0) {
            f.append(",")
        }
    }
    return toPersianNumber(f.toString())
}

fun toPersianNumber(text: String): String? {
    if (TextUtils.isEmpty(text)) {
        return ""
    }
    val out = StringBuilder()
    val length = text.length
    for (i in 0 until length) {
        val c = text[i]
        if ('0' <= c && c <= '9') {
            val number = c.toString().toInt()
            out.append(persianNumbers.get(number))
        } else if (c == '٫' || c == ',') {
            out.append('،')
        } else {
            out.append(c)
        }
    }
    return out.toString()
}

