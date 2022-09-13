package com.android.sivano.common.helpers

import android.text.TextUtils
import android.util.Patterns


object MyValidation {
    fun isValidEmail(email: String): Boolean {
        if (email != "") {
            //for mail
            val mailContainCars = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            val checkMailNumbers = email.split("@")[0]
            val checkIfMailNumbers: Int? = checkMailNumbers.toIntOrNull()

            return when {
                mailContainCars -> {
                    false
                }
                email.length < 14 -> {
                    false
                }
                checkIfMailNumbers != null -> {
                    false
                }
                else -> {
                    true
                }
            }
        } else {
            return false
        }
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}