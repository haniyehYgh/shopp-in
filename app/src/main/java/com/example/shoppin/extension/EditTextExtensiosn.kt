package com.example.shoppin.extension

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import java.text.DecimalFormat
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.pow


const val MIN_AMOUNT = 4


fun EditText.afterTextChangedFlow(): Flow<Editable?> = callbackFlow {
    val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            trySend(s)
        }

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int, count: Int, after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence?,
            start: Int, before: Int, count: Int
        ) {
        }
    }
    addTextChangedListener(watcher)
    awaitClose { removeTextChangedListener(watcher) }
}.debounce(100)


fun EditText.isValidSize(): Boolean {
    return when (inputType) {
        InputType.TYPE_CLASS_NUMBER -> !text.isNullOrBlank() && (text.length == MIN_AMOUNT)
        else ->  !text.isNullOrBlank()
    }
}



fun addSeparatorToEditText(editText: EditText) {
    val decimalFormat = DecimalFormat("#,###")

    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                if (s.isNotEmpty()) {
                    editText.removeTextChangedListener(this)
                    val cleanText = s.toString().replace(",", "")
                    val formattedText = decimalFormat.format(cleanText.toDouble())
                    editText.setText(formattedText)
                    editText.setSelection(formattedText.length)
                    editText.addTextChangedListener(this)
                }
            }
        }
    })
}





