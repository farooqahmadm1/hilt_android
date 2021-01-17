package com.ibex.fleetmanager.common.utility

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object Validations {

    fun watcher(editText: TextInputEditText, input: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (editText.text.toString().isNotEmpty()) {
                    input.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

}


fun watcher(editText: TextInputEditText, input: TextInputLayout): TextWatcher {
    return object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (editText.text.toString().isNotEmpty()) {
                input.error = null
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}